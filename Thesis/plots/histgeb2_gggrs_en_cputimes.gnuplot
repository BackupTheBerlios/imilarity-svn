set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0:0.6]
set y2range [5000:500000]
set log y2 2
set format y2 "2^{%L}"
set y2tics

set ylabel "GGGR" 1,0
set y2label "rekentijd in ms" -2,0

set xrange [0:23]
set noxtics
set label 1 "M_{1a}" at 0.5, graph -0.05, 0 centre norotate
set label 2 "M_{1b}" at 1.5, graph -0.05, 0 centre norotate
set label 3 "M_{1c}" at 2.5, graph -0.05, 0 centre norotate
set label 4 "M_2" at 3.5, graph -0.05, 0 centre norotate
set label 5 "M_3" at 4.5, graph -0.05, 0 centre norotate
set label 6 "M_5" at 5.5, graph -0.05, 0 centre norotate
set label 7 "M_{5c}" at 6.5, graph -0.05, 0 centre norotate
set label 8 "M_6" at 7.5, graph -0.05, 0 centre norotate
set label 9 "M_{6c}" at 8.5, graph -0.05, 0 centre norotate
set label 10 "M_7" at 9.5, graph -0.05, 0 centre norotate
set label 11 "M_{7c}" at 10.5, graph -0.05, 0 centre norotate
set label 12 "M_8" at 11.5, graph -0.05, 0 centre norotate
set label 13 "M_{8c}" at 12.5, graph -0.05, 0 centre norotate
set label 14 "M_9" at 13.5, graph -0.05, 0 centre norotate
set label 15 "M_{9c}" at 14.5, graph -0.05, 0 centre norotate
set label 16 "M_{10}" at 15.5, graph -0.05, 0 centre norotate
set label 17 "M_{10c}" at 16.5, graph -0.05, 0 centre norotate
set label 18 "M_{11}" at 17.5, graph -0.05, 0 centre norotate
set label 19 "M_{11c}" at 18.5, graph -0.05, 0 centre norotate
set label 20 "M_{12}" at 19.5, graph -0.05, 0 centre norotate
set label 21 "M_{13}" at 20.5, graph -0.05, 0 centre norotate
set label 22 "M_{I3}" at 21.5, graph -0.05, 0 centre norotate
set label 23 "M_{I3c}" at 22.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 12
set output "histgeb2_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.25
plot "-" using ($1+0.30):2 title "rekentijd XYZ" linetype 2 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd Yxy" linetype 3 axes x1y2,\
	 "-" using ($1+0.70):2 title "rekentijd L*a*b*" linetype 4 axes x1y2,\
 	 "-" using ($1+0.30):3 title "GGGR XYZ" linetype 5 axes x1y1,\
         "-" using ($1+0.50):3 title "GGGR Yxy" linetype 6 axes x1y1,\
         "-" using ($1+0.70):3 title "GGGR L*a*b*" linetype 7 axes x1y1

0	33365	0.04588744588744589
1	32892	0.07164502164502164
2	33049	0.07922077922077922
3	33188	0.07424242424242423
4	32906	0.030519480519480523
5	32798	0.195021645021645
6	32961	0.19848484848484846
7	33062	0.030519480519480523
8	33234	0.046536796536796536
9	33479	0.04069264069264069
10	33180	0.058008658008658016
11	33112	0.04025974025974026
12	33006	0.06601731601731602
13	33167	0.08008658008658008
14	33299	0.05844155844155845
15	33147	0.06688311688311688
16	33037	0.06645021645021644
17	33471	0.195021645021645
18	33600	0.19848484848484846
19	33369	0.058008658008658016
20	33452	0.06601731601731602
21	33138	0.008008658008658008
22	33116	0.0461038961038961
e
0	36704	0.045454545454545456
1	36639	0.0658008658008658
2	36307	0.07272727272727274
3	36461	0.08181818181818182
4	36106	0.03939393939393939
5	36260	0.1712121212121212
6	36201	0.16753246753246753
7	36194	0.03939393939393939
8	36512	0.04567099567099567
9	36278	0.04069264069264069
10	36362	0.051515151515151514
11	36238	0.0406926406926407
12	36838	0.05324675324675324
13	36121	0.05367965367965367
14	35897	0.051515151515151514
15	35927	0.051948051948051945
16	36974	0.053030303030303025
17	36058	0.1712121212121212
18	36356	0.16753246753246753
19	36177	0.051515151515151514
20	36085	0.05324675324675324
21	36201	0.0158008658008658
22	36207	0.04567099567099567
e
0	161061	0.06428571428571428
1	161353	0.07705627705627706
2	161086	0.08116883116883117
3	161292	0.0813852813852814
4	160950	0.0593073593073593
5	160833	0.20974025974025973
6	160738	0.20454545454545456
7	160865	0.0593073593073593
8	161135	0.06406926406926407
9	160954	0.06385281385281384
10	161288	0.07705627705627704
11	161069	0.06601731601731602
12	161162	0.06688311688311688
13	160980	0.09502164502164502
14	161530	0.07727272727272726
15	161178	0.07251082251082251
16	161315	0.06666666666666668
17	161471	0.1937229437229437
18	161508	0.20454545454545456
19	161531	0.07705627705627704
20	162016	0.06688311688311688
21	161278	0.04588744588744589
22	161241	0.06406926406926407
e
0	33365	0.04588744588744589
1	32892	0.07164502164502164
2	33049	0.07922077922077922
3	33188	0.07424242424242423
4	32906	0.030519480519480523
5	32798	0.195021645021645
6	32961	0.19848484848484846
7	33062	0.030519480519480523
8	33234	0.046536796536796536
9	33479	0.04069264069264069
10	33180	0.058008658008658016
11	33112	0.04025974025974026
12	33006	0.06601731601731602
13	33167	0.08008658008658008
14	33299	0.05844155844155845
15	33147	0.06688311688311688
16	33037	0.06645021645021644
17	33471	0.195021645021645
18	33600	0.19848484848484846
19	33369	0.058008658008658016
20	33452	0.06601731601731602
21	33138	0.008008658008658008
22	33116	0.0461038961038961
e
0	36704	0.045454545454545456
1	36639	0.0658008658008658
2	36307	0.07272727272727274
3	36461	0.08181818181818182
4	36106	0.03939393939393939
5	36260	0.1712121212121212
6	36201	0.16753246753246753
7	36194	0.03939393939393939
8	36512	0.04567099567099567
9	36278	0.04069264069264069
10	36362	0.051515151515151514
11	36238	0.0406926406926407
12	36838	0.05324675324675324
13	36121	0.05367965367965367
14	35897	0.051515151515151514
15	35927	0.051948051948051945
16	36974	0.053030303030303025
17	36058	0.1712121212121212
18	36356	0.16753246753246753
19	36177	0.051515151515151514
20	36085	0.05324675324675324
21	36201	0.0158008658008658
22	36207	0.04567099567099567
e
0	161061	0.06428571428571428
1	161353	0.07705627705627706
2	161086	0.08116883116883117
3	161292	0.0813852813852814
4	160950	0.0593073593073593
5	160833	0.20974025974025973
6	160738	0.20454545454545456
7	160865	0.0593073593073593
8	161135	0.06406926406926407
9	160954	0.06385281385281384
10	161288	0.07705627705627704
11	161069	0.06601731601731602
12	161162	0.06688311688311688
13	160980	0.09502164502164502
14	161530	0.07727272727272726
15	161178	0.07251082251082251
16	161315	0.06666666666666668
17	161471	0.1937229437229437
18	161508	0.20454545454545456
19	161531	0.07705627705627704
20	162016	0.06688311688311688
21	161278	0.04588744588744589
22	161241	0.06406926406926407
e


