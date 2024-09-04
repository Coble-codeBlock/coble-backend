package com.example.coblebackend.domain.project.domain

import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.global.entity.BaseIdEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_project")
@Entity
class Project(

    var image: String,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(100)")
    var description: String,

    var codeFile: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    var isShare: Boolean,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val createDate: LocalDate,
): BaseIdEntity() {

    fun updateProjectInfo(image: String, title: String, description: String) {
        this.image = image
        this.title = title
        this.description = description
    }

    fun updateCodeFile(codeFile: String) {
        this.codeFile = codeFile
    }

    fun updateShareStatus(isShare: Boolean) {
        this.isShare = isShare
    }
}