package io.datawire.loom.model

import io.datawire.loom.exception.LoomException

class ResourceExistsException(val reference: EntityReference,
                              message: String? = null) : LoomException(message = message)

fun modelExists(id: String) = exists(ModelReference(id))
fun exists(ref: ModelReference) = ResourceExistsException(ref, "fabric model '${ref.id}' already exists")

fun fabricExists(name: String) = exists(FabricReference(name))
fun exists(ref: FabricReference) = ResourceExistsException(ref, "fabric '${ref.id}' already exists")

fun clusterExists(name: String) = exists(ClusterReference(name))
fun exists(ref: ClusterReference) = ResourceExistsException(ref, "cluster '${ref.id}' already exists")
