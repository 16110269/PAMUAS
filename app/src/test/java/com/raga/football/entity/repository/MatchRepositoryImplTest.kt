package com.raga.football.entity.repository

import com.raga.football.rest.FootballRest
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by muhrahmatullah on 05/09/18.
 */
class MatchRepositoryImplTest {

    @Mock
    lateinit var footballRest: FootballRest

    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = MatchRepositoryImpl(footballRest)
    }

    @Test
    fun getEventById() {
        matchRepositoryImpl.getEventById("4328")
        verify(footballRest).getEventById("4328")
    }

    @Test
    fun getUpcomingMatch() {
        matchRepositoryImpl.getUpcomingMatch("4328")
        verify(footballRest).getUpcomingMatch("4328")
    }

    @Test
    fun getFootballMatch() {
        matchRepositoryImpl.getFootballMatch("4328")
        verify(footballRest).getLastmatch("4328")
    }
}