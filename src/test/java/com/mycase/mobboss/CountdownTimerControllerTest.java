package com.mycase.mobboss;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CountdownTimerControllerTest {
    @Test(groups = {"unit"})
    public void testGetTimeString() throws Exception {
        CountdownTimerController uut = new CountdownTimerController(null, 60);
        uut.setTimeLeft(0);
        Assert.assertEquals(uut.getTimeString(),"00:00");
        uut.setTimeLeft(6);
        Assert.assertEquals(uut.getTimeString(),"00:06");
        uut.setTimeLeft(26);
        Assert.assertEquals(uut.getTimeString(),"00:26");
        uut.setTimeLeft(126);
        Assert.assertEquals(uut.getTimeString(),"02:06");
        uut.setTimeLeft(3666);
        Assert.assertEquals(uut.getTimeString(),"61:06");
    }
}
