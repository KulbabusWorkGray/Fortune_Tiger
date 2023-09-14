sealed class Modules(
    val nameSpace : String,
    val path : String
) {
    object App : Modules(
        nameSpace = "${Android.nameSpace}",
        path =":app"
    )
    object Slots : Modules(
        nameSpace = "${Android.nameSpace}.slots",
        path =":slots"
    )
    object Target : Modules(
        nameSpace = "${Android.nameSpace}.target",
        path =":target"
    )
    object Theme : Modules(
        nameSpace = "${Android.nameSpace}.theme",
        path =":theme"
    )
}