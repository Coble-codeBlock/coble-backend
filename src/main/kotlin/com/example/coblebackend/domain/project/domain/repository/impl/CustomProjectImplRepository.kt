package com.example.coblebackend.domain.project.domain.repository.impl

import com.example.coblebackend.domain.like.domain.QLike
import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.QProject
import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.domain.user.presentation.dto.response.UserProjectListElement
import com.example.coblebackend.global.utils.S3Util
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomProjectImplRepository(
    private val queryFactory: JPAQueryFactory,
    private val s3Util: S3Util,
) : CustomProjectRepository {

    override fun findProjectList(pageable: Pageable): Page<Project> {
        val qProject = QProject.project
        val qLike = QLike.like

        val query = queryFactory
            .selectFrom(qProject)
            .leftJoin(qLike).on(qLike.project().id.eq(qProject.id))
            .groupBy(qProject.id)
            .orderBy(qLike.count().desc())

        val totalResults = query.fetchCount()
        val results = query
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl(results, pageable, totalResults)
    }

    override fun findUserLikeProjectList(user: User): List<UserProjectListElement> {
        val qProject = QProject.project
        val qLike = QLike.like

        val projects = queryFactory
            .selectFrom(qProject)
            .leftJoin(qLike).on(qLike.project().id.eq(qProject.id))
            .groupBy(qProject.id)
            .orderBy(qLike.count().desc())
            .fetch()

        val userProjectList = projects.map { project ->
            val likeStatusQuery = queryFactory
                .selectOne()
                .from(qLike)
                .where(
                    qLike.user().id.eq(user.id)
                        .and(qLike.project().id.eq(project.id))
                )
                .fetchFirst()

            val likeStatus = likeStatusQuery != null

            val imageUrl = s3Util.getS3ObjectUrl(project.image)
            val profileUrl = s3Util.getS3ObjectUrl(user.profile)


            UserProjectListElement(
                id = project.id,
                image = imageUrl,
                profile = profileUrl,
                title = project.title,
                description = project.description,
                likeStatus = likeStatus
            )
        }

        return userProjectList
    }

}
