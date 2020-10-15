package com.circularuins.mvvmcleanarchitecture2020.infra

import com.circularuins.mvvmcleanarchitecture2020.infra.schema.Master

fun Master.convert(): com.circularuins.mvvmcleanarchitecture2020.domain.model.Master =
    com.circularuins.mvvmcleanarchitecture2020.domain.model.Master(
        name ?: "",
        data?.let {
            val regex = Regex(ApiService.BASE_URL + """([\w]+)\.json""")
            val match = regex.matchEntire(it)
            match?.let { m ->
                val matches = m.groupValues
                if (matches.size <= 1) return@let ""
                matches[1]
            } ?: ""
        } ?: ""
    )