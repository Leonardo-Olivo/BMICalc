package ch.olivo.leonardo.bmicalc;

import android.content.Intent;
import android.os.IBinder;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ServiceTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import ch.olivo.leonardo.bmicalc.enums.BMIClass;
import ch.olivo.leonardo.bmicalc.service.BMIService;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BMIServiceTest {

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void testWithBoundService() throws TimeoutException {
        Intent serviceIntent =
                new Intent(ApplicationProvider.getApplicationContext(), BMIService.class);
        serviceIntent.putExtra(BMIService.SEED_KEY, 42L);
        IBinder binder = mServiceRule.bindService(serviceIntent);
        BMIService service = ((BMIService.BMIServiceBinder) binder).getService();

        double weight = 75;
        double height = 1.85;
        double expectedBMI = 21.91;
        BMIClass expectedClass = BMIClass.NORMAL_WEIGHT;

        double result = service.calculateBMI(weight, height);

        assertEquals(expectedBMI, result, 0);

        BMIClass resultClass = service.findBMIClass(result);

        assertEquals(expectedClass, resultClass);
    }
}
