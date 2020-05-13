package com.tinkooladik.airqualityindex.domain

interface MapperFrom<in FROM, out TO> {

    fun mapFrom(item: FROM): TO
}

interface MapperTo<in FROM, out TO> {

    fun mapTo(item: FROM): TO
}