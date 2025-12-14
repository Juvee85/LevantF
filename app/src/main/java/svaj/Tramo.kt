package svaj

import android.os.Parcel
import android.os.Parcelable

data class Tramo(
    var segmento: String = "Subida",
    var ejeX: Int = 0,
    var ecuacion: String = "Cicloidal",
    var altura: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(segmento)
        parcel.writeInt(ejeX)
        parcel.writeString(ecuacion)
        parcel.writeDouble(altura)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tramo> {
        override fun createFromParcel(parcel: Parcel): Tramo {
            return Tramo(parcel)
        }

        override fun newArray(size: Int): Array<Tramo?> {
            return arrayOfNulls(size)
        }
    }
}