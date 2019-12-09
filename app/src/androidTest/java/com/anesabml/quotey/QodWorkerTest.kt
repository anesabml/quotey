package com.anesabml.quotey

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.anesabml.quotey.framework.workers.QodWorker
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class QodWorkerTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testQodWorker() {
        val worker = TestListenableWorkerBuilder<QodWorker>(context).build()

        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }
}