package com.palizpars


import com.tinder.scarlet.Event
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface PalizparsSocketService {

    @Receive
    fun observeEvent(): Flowable<Event>

    @Receive
    fun observeResponse(): Flowable<PalizparsBaseSocketResponseModel>

    @Send
    fun <T> sendData(palizparsBaseSocketModel: PalizparsBaseSocketModel<T>)


}