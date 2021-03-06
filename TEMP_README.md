Ignore all the fabric model stuff that's ops-centric. To get a cluster in Loom:

Bake Docker Image

```./alpha_bake.sh``` produces `datawire/loom` image.

Run Loom

```
docker run --rm -it \
    -p 7000:7000 \
    -e AWS_ACCESS_KEY_ID=<Your-AWS-API-Access-Key> \
    -e AWS_SECRET_ACCESS_KEY=<Your-AWS-API-Secret-Key> \
    -e AWS_REGION=us-east-1 \
    datawire/loom
```

Fabric Setup

```bash
# substitute "flynn", or "rdl" or whatever in the "name" field of the JSON below. DO NOT EDIT "model".

curl -X POST -H "Content-Type: application/json" --data '{"name"  : "plombardi", "model" : "simple-v1"}' localhost:7000/fabrics
```

Cluster Credentials

```bash
# Get your credentials for kubectl usag ($name should be set to the value in "name" field of the previous JSON).
# The mkdir bit is an unfortunate issue with kubectl and its config format. I can explain that more some other time.

mkdir -p ~/.kube/config.d
curl --output=~/.kube/config.d/${name}.cluster
     localhost:7000/fabrics/$name/cluster/config
```

Cluster Teardown

```
curl -X DELETE localhost:7000/fabrics/:name/cluster
```

Kubectl

The Cluster status API isn't complete so you'll just have to keep poking this endpoint until cluster forms (takes about 3min).

```
kubectl cluster-info --kubeconfig={$HOME}/.kube/config.d/${name}.cluster
```
