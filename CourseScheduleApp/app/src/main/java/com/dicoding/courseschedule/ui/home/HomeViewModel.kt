package com.dicoding.courseschedule.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType

class HomeViewModel(private val repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    // Reference:
    // https://www.dicoding.com/academies/287/discussions/121050?#comment-321685
    fun getNearestSchedule(queryType: QueryType): LiveData<Course?> {
        val nearestCourse = Transformations.switchMap(MutableLiveData(queryType)) {
            repository.getNearestSchedule(it)
        }

        return nearestCourse
    }
}
