/*
 * Decompiled with CFR 0.152.
 */
package org.irri.breedingtool.rjava.manager.api;

public interface IRJavaPBToolsAnalysisManager {
    public void doNC1Test(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13);

    public void doNC1METest(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13);

    public void doNC2Test(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13);

    public void doNC2METest(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13);

    public void doNC3Test(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13);

    public void doNC3METest(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12);

    public void doTTCTest(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15, String var16);

    public void doTTCMETest(String var1, String var2, String var3, String[] var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15, String var16);

    public void doDiallel1Test(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel1METest(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel2Test(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel2METest(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel3Test(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel3METest(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel4Test(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doDiallel4METest(String var1, String var2, String var3, String var4, String[] var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15);

    public void doSingleEnvironmentAnalysis(String var1, String var2, String var3, int var4, String[] var5, String var6, String[] var7, String var8, String var9, String var10, String var11, String var12, boolean var13, boolean var14, boolean var15, boolean var16, boolean var17, String var18, String var19, boolean var20, boolean var21, boolean var22, String var23, String[] var24, String[] var25, boolean var26, boolean var27, boolean var28, boolean var29, boolean var30);

    public void doMultiEnvironmentOneStage(String var1, String var2, String var3, int var4, String[] var5, String var6, String[] var7, String var8, String var9, String var10, String var11, String var12, boolean var13, boolean var14, boolean var15, boolean var16, boolean var17, boolean var18, boolean var19, boolean var20, boolean var21, boolean var22, boolean var23, String var24, String[] var25, String[] var26, boolean var27, boolean var28, boolean var29);

    public void doMultiEnvironmentSecondStage(String var1, String var2, String var3, String var4, String[] var5, String[] var6, String[] var7, String[] var8, String var9, String[] var10, String var11, boolean var12, boolean var13, boolean var14, boolean var15, boolean var16, boolean var17, boolean var18, boolean var19, boolean var20, boolean var21, boolean var22, String var23, String[] var24, String[] var25, boolean var26, boolean var27, boolean var28);

    public void doMultiEnvironmentSecondStageVersion2(String var1, String var2, String var3, String[] var4, String var5, String[] var6, String var7, String[] var8, String[] var9);

    public void doSelectionIndex(String var1, String var2, String var3, int var4, int var5, boolean var6, String var7, String var8, String var9, int var10);

    public void doQtl(String var1, boolean var2, String var3, String var4, String var5, String var6, int var7, String[] var8, String var9, String var10, String var11, String var12, String var13, String var14, String[] var15, String var16, boolean var17, String var18, String var19, String var20, String var21, String var22, boolean var23, String var24, boolean var25, double var26, double var28, double var30);

    public void doGenerationMeanRawData(String var1, String var2, String[] var3, String[] var4, String var5);

    public void doGenerationMeanSummaryStats(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String[] var8, String[] var9, String var10);

    public void doHWETest(String var1, String var2);

    public void dogenDistance(String var1, String var2, String var3, int var4, Boolean var5, Boolean var6);

    public void dopopStructure(String var1, String var2, Boolean var3);
}

