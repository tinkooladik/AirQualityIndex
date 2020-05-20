package com.tinkooladik.airqualityindex.common.protocol

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiredPermissions(
    val permissions: Array<String>
)

interface PermissionsProtocol {

    fun getRequiredPermissions(): Array<String>? =
        javaClass.getAnnotation(RequiredPermissions::class.java)?.permissions
}