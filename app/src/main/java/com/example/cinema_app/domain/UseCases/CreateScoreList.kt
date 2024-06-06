package com.example.cinema_app.domain.UseCases

import com.example.cinema_app.data.Api.DataClasses.FootballEvent.ScoreItem

class CreateScoreList {
    fun create(type:String, firstTimeScore:String?, fullTimeScore:String?, extraTimeScore:String?,
               penaltyScore:String?, firstPeriodScore:String?, secondPeriodScore:String?,
               thirdPeriodScore:String?, fourthPeriodScore:String?, fifthPeriodScore:String?):List<ScoreItem>{
        val list: MutableList<ScoreItem> = mutableListOf()

        if(type == "футбол")
        {
            list.add(ScoreItem("Первый тайм:", firstTimeScore))
            list.add(ScoreItem("Основное время:", fullTimeScore))
            if(extraTimeScore!=null)
                list.add(ScoreItem("Дополнительное время:", extraTimeScore))
            if(penaltyScore != null)
                list.add(ScoreItem("Пенальти:", extraTimeScore))
        }

        if(type == "баскетбол")
        {
            list.add(ScoreItem("Первый период:", firstPeriodScore))
            list.add(ScoreItem("Второй период:", secondPeriodScore))
            list.add(ScoreItem("Третий период:", thirdPeriodScore))
            list.add(ScoreItem("Четвёртый период:", fourthPeriodScore))
            if(extraTimeScore != null)
                list.add(ScoreItem("Овертайм:", extraTimeScore))
        }

        if(type == "волейбол")
        {
            list.add(ScoreItem("Первый период:", firstPeriodScore))
            list.add(ScoreItem("Второй период:", secondPeriodScore))
            list.add(ScoreItem("Третий период:", thirdPeriodScore))
            if(fourthPeriodScore != null)
                list.add(ScoreItem("Четвёртый период:", fourthPeriodScore))
            if(fifthPeriodScore != null)
                list.add(ScoreItem("Пятый период:", fifthPeriodScore))
        }

        if(type == "хоккей")
        {
            list.add(ScoreItem("Первый период:", firstPeriodScore))
            list.add(ScoreItem("Второй период:", secondPeriodScore))
            list.add(ScoreItem("Третий период:", thirdPeriodScore))
            if(extraTimeScore != null)
                list.add(ScoreItem("Овертайм:", extraTimeScore))
            if(penaltyScore != null)
                list.add(ScoreItem("Серия буллитов:", penaltyScore))
        }

        return list
    }
}