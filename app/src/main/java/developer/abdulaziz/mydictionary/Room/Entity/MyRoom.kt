package developer.abdulaziz.mydictionary.Room.Entity

import androidx.room.*

@Entity
class MyRoom {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var image: String? = null
    var english: String? = null
    var uzbek: String? = null
    var category: String? = null
    var like: Boolean? = null

    constructor()
    constructor(
        id: Int?,
        image: String?,
        english: String?,
        uzbek: String?,
        category: String?,
        like: Boolean?
    ) {
        this.id = id
        this.image = image
        this.english = english
        this.uzbek = uzbek
        this.category = category
        this.like = like
    }

    constructor(
        image: String?,
        english: String?,
        uzbek: String?,
        category: String?,
        like: Boolean?
    ) {
        this.image = image
        this.english = english
        this.uzbek = uzbek
        this.category = category
        this.like = like
    }

}