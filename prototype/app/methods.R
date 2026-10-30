# Text of the Methods page: what each analysis does, and the references for the methods (not for the software; the
# software references are built from the installed packages in app.R). Keep this in step with the engine code:
# engine/R/star_aov.R, star_splitplot.R, star_helpers.R, star_diagnostics.R, star_correlation.R, star_regression.R.

methods_sections <- tagList(
  h4("การวิเคราะห์ความแปรปรวน: แผนการทดลองแบบสุ่มสมบูรณ์ (CRD)"),
  p("โมเดล (อิทธิพลทั้งหมดเป็นแบบคงที่): ", code("y ~ A"), " สำหรับปัจจัยเดียว และ ", code("y ~ A * B"), " สำหรับสองปัจจัยพร้อมปฏิสัมพันธ์ ประมาณค่าด้วย ",
    code("aov()"), " (Chambers et al., 1992) ทดสอบแต่ละอิทธิพลด้วย F = MS(อิทธิพล) / MS(error) แถวที่ไม่มีค่าตัวแปรตอบสนองจะถูกตัดออก ",
    "ตารางใช้ผลบวกกำลังสองแบบลำดับ (Type I) เช่นเดียวกับ ", code("aov()"), " ดังนั้นเมื่อมีสองปัจจัยและจำนวนข้อมูลในแต่ละกลุ่มไม่เท่ากัน ผลของปัจจัยที่สองจะขึ้นกับลำดับที่ใส่ปัจจัย"),
  p("CV (%) = 100 x sqrt(MS error) / ค่าเฉลี่ยรวม, Partial eta-squared = SS(อิทธิพล) / (SS(อิทธิพล) + SS(error)) ",
    "ในตารางค่าเฉลี่ย SE คือส่วนเบี่ยงเบนมาตรฐานของกลุ่มหารด้วยรากที่สองของจำนวนข้อมูลในกลุ่ม"),
  p("อ่านรายละเอียดเพิ่มเติมได้ใน Gomez & Gomez (1984)"),

  h4("การวิเคราะห์ความแปรปรวน: แผนการทดลองแบบสุ่มในบล็อกสมบูรณ์ (RCBD)"),
  p("โมเดล: ", code("y ~ Block + A"), " หรือ ", code("y ~ Block + A * B"), " บล็อกเป็นอิทธิพลแบบคงที่ เหมือนการวิเคราะห์ใน STAR ต้นฉบับ ",
    "ถ้าทุกคู่ บล็อก x สิ่งทดลอง มีจำนวนข้อมูลเท่ากันและไม่มีค่าตัวแปรตอบสนองหายไป จะประมาณโมเดลนี้ด้วย ", code("aov()"), " โดยตรง ",
    "ถ้าไม่เป็นเช่นนั้น จะใช้วิธีของ STAR ต้นฉบับ คือประมาณค่าที่หายไปและปรับองศาอิสระ (Gomez & Gomez, 1984) และจะไม่วิเคราะห์ข้อมูลที่มีโครงสร้างที่วิธีนี้รองรับไม่ได้"),

  h4("การวิเคราะห์ความแปรปรวน: แผนการทดลองแบบ split plot ใน RCBD"),
  p("โมเดล: ", code("y ~ Block + A * B + Error(Block:A)"), " ประมาณค่าด้วย ", code("aov()"), " (Chambers et al., 1992) โดย A เป็นปัจจัยหลัก (main plot) และ B เป็นปัจจัยรอง (sub plot) ",
    "Error(a) (บล็อก x A) ใช้ทดสอบ Block และ A ส่วน Error(b) (ค่าคลาดเคลื่อนที่เหลือ) ใช้ทดสอบ B และ A x B ค่า CV(a) และ CV(b) คำนวณจาก mean square ของ error ทั้งสอง ",
    "ข้อมูลต้องครบและสมดุล คือมีหนึ่งค่าสังเกตในทุกคู่ บล็อก x A x B"),
  p("การเปรียบเทียบค่าเฉลี่ยใน split plot: เปรียบเทียบ A ด้วย Error(a) และ B ด้วย Error(b) ค่าความคลาดเคลื่อนมาตรฐานคำนวณจากโมเดล sqrt(MS error / n) ",
    "เมื่อปฏิสัมพันธ์มีนัยสำคัญ จะเปรียบเทียบ B ภายในแต่ละระดับของ A ด้วย Error(b) และเปรียบเทียบ A ภายในแต่ละระดับของ B ด้วย error รวม ",
    "[MS(a) + (b - 1) MS(b)] / b ซึ่งองศาอิสระได้จากการประมาณของ Satterthwaite (Satterthwaite, 1946; Gomez & Gomez, 1984) และปัดลง"),

  h4("การเปรียบเทียบค่าเฉลี่ย (post hoc tests)"),
  p("Fisher's protected LSD (Fisher, 1935), Tukey's HSD (Tukey, 1949; ใช้ Tukey-Kramer, Kramer, 1956, เมื่อขนาดกลุ่มไม่เท่ากัน) และ Duncan's multiple range test, DMRT (Duncan, 1955) ",
    "คำนวณด้วยแพ็กเกจ agricolae โดยใช้ mean square และองศาอิสระของ error ที่เกี่ยวข้อง ที่ระดับนัยสำคัญที่เลือกในแอป ",
    "ค่าเฉลี่ยที่มีอักษรเดียวกันไม่แตกต่างกันอย่างมีนัยสำคัญ อักษรจะแสดงเฉพาะเมื่อการทดสอบ F ของอิทธิพลนั้นมีนัยสำคัญ ",
    "เมื่อมีสองปัจจัยและปฏิสัมพันธ์มีนัยสำคัญ จะเปรียบเทียบอิทธิพลอย่างง่าย (simple effects: แต่ละปัจจัยในแต่ละระดับของอีกปัจจัย) แทนค่าเฉลี่ยรวม ",
    "Automatic = ใช้ LSD เมื่อปัจจัยมีไม่เกิน 5 ระดับ ถ้ามากกว่านั้นใช้ Tukey HSD วิธีจัดการจำนวนซ้ำที่ไม่เท่ากันของแต่ละวิธีดูได้ในเอกสารของแพ็กเกจ agricolae"),

  h4("การตรวจสอบข้อสมมติ (ANOVA และการถดถอย)"),
  p("ประมาณโมเดลเชิงเส้นเดิมอีกครั้งแล้วตรวจสอบค่าส่วนเหลือ (residuals) (ยังไม่รองรับแผนการทดลองแบบ split plot) ",
    "ค่าผิดปกติ: ใช้ externally studentized residuals โดย |r| > 3 ถือเป็นค่าผิดปกติ และ 2 < |r| <= 3 ถือว่าน่าสงสัย (Belsley et al., 1980) และแสดง Cook's distance (Cook, 1977) ของแถวที่ถูกระบุ ",
    "การแจกแจงปกติของค่าส่วนเหลือ: Shapiro-Wilk test (Shapiro & Wilk, 1965) และ Kolmogorov-Smirnov test แบบปรับของ Lilliefors (Lilliefors, 1967) ",
    "ความเท่ากันของความแปรปรวนใน ANOVA: Levene test แบบใช้มัธยฐาน หรือ Brown-Forsythe (Brown & Forsythe, 1974) ระหว่างกลุ่มสิ่งทดลอง ",
    "ความแปรปรวนคงที่ในการถดถอย: Breusch-Pagan test (Breusch & Pagan, 1979) แบบ studentized (Koenker, 1981) เทียบกับค่าที่ประมาณได้ ",
    "การทดสอบเหล่านี้เป็นเพียงข้อแนะนำ ไม่ใช่ข้อพิสูจน์ เมื่อตัวอย่างน้อยจะมีอำนาจการทดสอบต่ำ และเมื่อตัวอย่างมากจะชี้ความเบี่ยงเบนเล็กน้อยที่ไม่มีความสำคัญในทางปฏิบัติ"),

  h4("สหสัมพันธ์ (correlation)"),
  p("สหสัมพันธ์แบบ Pearson, Spearman หรือ Kendall ของตัวแปรแต่ละคู่ด้วย ", code("cor.test()"), " (Pearson, 1895; Spearman, 1904; Kendall, 1938; Hollander & Wolfe, 1973; ",
    "ค่า p ของ Spearman's rho: Best & Roberts, 1975) แต่ละคู่ใช้แถวที่มีค่าทั้งสองตัวแปร และแสดงค่า N ของแต่ละคู่ ",
    "ช่วงความเชื่อมั่นของ Pearson's r ใช้การแปลง z ของ Fisher (Fisher, 1921) การปรับค่า p เมื่อทดสอบหลายคู่ (เลือกได้) ใช้ ",
    code("p.adjust()"), ": Holm (1979), Bonferroni หรือ Benjamini-Hochberg (1995) ",
    "คำว่า อ่อน ปานกลาง และสูง เป็นเกณฑ์คร่าว ๆ ของแอปนี้ (|r| ต่ำกว่า 0.1 แทบไม่มี, ต่ำกว่า 0.3 อ่อน, ต่ำกว่า 0.5 ปานกลาง, ต่ำกว่า 0.7 สูง, นอกนั้นสูงมาก) ไม่ใช่การทดสอบทางสถิติ"),

  h4("การถดถอย (regression)"),
  p("วิธีกำลังสองน้อยที่สุดด้วย ", code("lm()"), " (Wilkinson & Rogers, 1973; Chambers, 1992): เส้นตรง หรือพหุนามกำลังสองหรือกำลังสามของตัวแปรอิสระหนึ่งตัวโดยใช้ x, x^2, x^3 เป็นพจน์ หรือการถดถอยพหุคูณที่มีตัวแปรอิสระหลายตัว ",
    "รายงานการทดสอบ F ของโมเดล, R-squared, adjusted R-squared, root mean square error, CV และ AIC (Akaike, 1974) พร้อมการทดสอบ t และช่วงความเชื่อมั่นของสัมประสิทธิ์แต่ละตัว ",
    "สัมประสิทธิ์มาตรฐาน (เฉพาะโมเดลเส้นตรง) = b x SD(x) / SD(y) ค่า variance inflation factor (VIF) ของตัวแปรอิสระคือค่าในแนวทแยงที่ตรงกันของเมทริกซ์ผกผันของเมทริกซ์สหสัมพันธ์ระหว่างตัวแปรอิสระ ค่าที่เกิน 10 จะถูกระบุ (Belsley et al., 1980) ",
    "สำหรับพหุนามกำลังสอง จุดคงที่ (stationary point) คือ -b1 / (2 b2) และจะระบุว่าเป็นการประมาณนอกช่วง (extrapolation) ถ้าอยู่นอกช่วงของข้อมูล ",
    "ค่าทำนาย (ตัวแปรอิสระหนึ่งตัว) คือค่าเฉลี่ยที่ประมาณได้พร้อมช่วงความเชื่อมั่น 95% และค่าที่อยู่นอกช่วงของข้อมูลจะถูกระบุว่าเป็นการประมาณนอกช่วง"),

  h4("การจัดการข้อมูล"),
  p("ไฟล์ที่อัปโหลดจะถูกอ่านในเบราว์เซอร์ ตัวเลขที่มีตัวคั่นหลักพันหรือหน่วย (เช่น 1,250.5 หรือ 12 kg) จะถูกแปลงเป็นตัวเลข ค่าอย่าง n/a ถือเป็นค่าที่หายไป และกล่อง Data checks ในแต่ละหน้าจอจะแสดงรายการที่ถูกแปลงหรือตัดออก")
)

# references for the methods above (software references are generated from the packages in app.R)
method_refs <- c(
  "Akaike H (1974). A new look at the statistical model identification. IEEE Transactions on Automatic Control, 19(6), 716-723. https://doi.org/10.1109/TAC.1974.1100705",
  "Belsley DA, Kuh E, Welsch RE (1980). Regression Diagnostics: Identifying Influential Data and Sources of Collinearity. Wiley, New York.",
  "Benjamini Y, Hochberg Y (1995). Controlling the false discovery rate: a practical and powerful approach to multiple testing. Journal of the Royal Statistical Society B, 57(1), 289-300. https://doi.org/10.1111/j.2517-6161.1995.tb02031.x",
  "Best DJ, Roberts DE (1975). Algorithm AS 89: the upper tail probabilities of Spearman's rho. Applied Statistics, 24(3), 377-379.",
  "Breusch TS, Pagan AR (1979). A simple test for heteroscedasticity and random coefficient variation. Econometrica, 47(5), 1287-1294. https://doi.org/10.2307/1911963",
  "Brown MB, Forsythe AB (1974). Robust tests for the equality of variances. Journal of the American Statistical Association, 69(346), 364-367. https://doi.org/10.1080/01621459.1974.10482955",
  "Chambers JM (1992). Linear models. Chapter 4 in Chambers JM, Hastie TJ (eds), Statistical Models in S. Wadsworth & Brooks/Cole, Pacific Grove.",
  "Chambers JM, Freeny A, Heiberger RM (1992). Analysis of variance; designed experiments. Chapter 5 in Chambers JM, Hastie TJ (eds), Statistical Models in S. Wadsworth & Brooks/Cole, Pacific Grove.",
  "Cook RD (1977). Detection of influential observation in linear regression. Technometrics, 19(1), 15-18. https://doi.org/10.1080/00401706.1977.10489493",
  "Duncan DB (1955). Multiple range and multiple F tests. Biometrics, 11(1), 1-42. https://doi.org/10.2307/3001478",
  "Fisher RA (1921). On the 'probable error' of a coefficient of correlation deduced from a small sample. Metron, 1(4), 3-32.",
  "Fisher RA (1935). The Design of Experiments. Oliver and Boyd, Edinburgh.",
  "Gomez KA, Gomez AA (1984). Statistical Procedures for Agricultural Research, 2nd edition. Wiley, New York.",
  "Hollander M, Wolfe DA (1973). Nonparametric Statistical Methods. Wiley, New York.",
  "Holm S (1979). A simple sequentially rejective multiple test procedure. Scandinavian Journal of Statistics, 6(2), 65-70.",
  "Kendall MG (1938). A new measure of rank correlation. Biometrika, 30(1-2), 81-93. https://doi.org/10.2307/2332226",
  "Koenker R (1981). A note on studentizing a test for heteroscedasticity. Journal of Econometrics, 17(1), 107-112. https://doi.org/10.1016/0304-4076(81)90062-2",
  "Kramer CY (1956). Extension of multiple range tests to group means with unequal numbers of replications. Biometrics, 12(3), 307-310. https://doi.org/10.2307/3001469",
  "Lilliefors HW (1967). On the Kolmogorov-Smirnov test for normality with mean and variance unknown. Journal of the American Statistical Association, 62(318), 399-402. https://doi.org/10.1080/01621459.1967.10482916",
  "Pearson K (1895). Notes on regression and inheritance in the case of two parents. Proceedings of the Royal Society of London, 58, 240-242.",
  "Satterthwaite FE (1946). An approximate distribution of estimates of variance components. Biometrics Bulletin, 2(6), 110-114. https://doi.org/10.2307/3002019",
  "Shapiro SS, Wilk MB (1965). An analysis of variance test for normality (complete samples). Biometrika, 52(3-4), 591-611. https://doi.org/10.2307/2333709",
  "Spearman C (1904). The proof and measurement of association between two things. The American Journal of Psychology, 15(1), 72-101. https://doi.org/10.2307/1412159",
  "Tukey JW (1949). Comparing individual means in the analysis of variance. Biometrics, 5(2), 99-114. https://doi.org/10.2307/3001913",
  "Wilkinson GN, Rogers CE (1973). Symbolic description of factorial models for analysis of variance. Applied Statistics, 22(3), 392-399. https://doi.org/10.2307/2346786"
)
