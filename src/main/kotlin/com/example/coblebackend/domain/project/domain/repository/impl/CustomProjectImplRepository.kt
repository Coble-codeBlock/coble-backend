package com.example.coblebackend.domain.project.domain.repository.impl

import com.example.coblebackend.domain.like.domain.QLike
import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.QProject
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomProjectImplRepository(
    private val queryFactory: JPAQueryFactory
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

}

