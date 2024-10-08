package io.github.dkexception.kmm.aqiapp.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.MemoryCountersMetric
import androidx.benchmark.macro.PowerMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "io.github.dkexception.kmm.aqiapp",
        metrics = listOf(
            StartupTimingMetric(),
            PowerMetric(type = PowerMetric.Type.Battery()),
            MemoryCountersMetric()
        ),
        iterations = 3,
        startupMode = StartupMode.COLD
    ) {
        dropKernelPageCache()
        dropShaderCache()
        pressHome()
        startActivityAndWait()
        killProcess()
    }
}
