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
set output "histgeb1_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.25
plot "-" using ($1+0.30):2 title "rekentijd HSV" linetype 2 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd I1I2I3" linetype 3 axes x1y2,\
	 "-" using ($1+0.70):2 title "rekentijd Irb" linetype 4 axes x1y2,\
 	 "-" using ($1+0.30):3 title "GGGR HSV" linetype 5 axes x1y1,\
	 "-" using ($1+0.50):3 title "GGGR I1I2I3" linetype 6 axes x1y1,\
         "-" using ($1+0.70):3 title "GGGR Irb" linetype 7 axes x1y1

0	39796	0.03398268398268398
1	38927	0.04502164502164502
2	38796	0.04632034632034632
3	39092	0.04632034632034632
4	39000	0.028787878787878786
5	39414	0.17164502164502163
6	39357	0.16904761904761903
7	40597	0.028787878787878786
8	39941	0.03549783549783549
9	39096	0.034199134199134194
10	39107	0.07662337662337664
11	39144	0.022943722943722943
12	39208	0.03528138528138529
13	38968	0.08268398268398268
14	39046	0.07705627705627706
15	39278	0.05779220779220779
16	39183	0.03701298701298701
17	40335	0.17164502164502163
18	39992	0.16904761904761903
19	39917	0.07662337662337664
20	39882	0.03528138528138529
21	39095	0.02683982683982684
22	39129	0.03463203463203463
e
0	34644	0.04047619047619047
1	33769	0.051948051948051945
2	33705	0.05432900432900432
3	33528	0.05476190476190476
4	33650	0.04783549783549783
5	33526	0.19199134199134196
6	33503	0.19956709956709953
7	33927	0.04783549783549783
8	34065	0.04069264069264069
9	33780	0.053896103896103886
10	33687	0.06818181818181818
11	33769	0.04415584415584415
12	33226	0.047402597402597405
13	33412	0.07727272727272728
14	33163	0.06883116883116883
15	33224	0.05974025974025974
16	33341	0.047835497835497835
17	33559	0.19199134199134196
18	33676	0.19956709956709953
19	33821	0.06818181818181818
20	33375	0.047402597402597405
21	33558	0.03376623376623377
22	33436	0.04069264069264069
e
0	34251	0.04177489177489177
1	33259	0.05108225108225108
2	33450	0.05194805194805194
3	33215	0.04696969696969697
4	33463	0.030952380952380953
5	33212	0.17554112554112553
6	33335	0.16969696969696968
7	33816	0.030952380952380953
8	34006	0.04199134199134199
9	36029	0.034848484848484844
10	33987	0.06168831168831169
11	33367	0.012554112554112555
12	33507	0.02359307359307359
13	33347	0.06233766233766234
14	33813	0.06168831168831169
15	33467	0.024025974025974027
16	33490	0.02402597402597402
17	33694	0.17554112554112553
18	33801	0.16969696969696968
19	33797	0.06168831168831169
20	33934	0.02359307359307359
21	33745	0.009740259740259742
22	33767	0.04199134199134199
e
0	39796	0.03398268398268398
1	38927	0.04502164502164502
2	38796	0.04632034632034632
3	39092	0.04632034632034632
4	39000	0.028787878787878786
5	39414	0.17164502164502163
6	39357	0.16904761904761903
7	40597	0.028787878787878786
8	39941	0.03549783549783549
9	39096	0.034199134199134194
10	39107	0.07662337662337664
11	39144	0.022943722943722943
12	39208	0.03528138528138529
13	38968	0.08268398268398268
14	39046	0.07705627705627706
15	39278	0.05779220779220779
16	39183	0.03701298701298701
17	40335	0.17164502164502163
18	39992	0.16904761904761903
19	39917	0.07662337662337664
20	39882	0.03528138528138529
21	39095	0.02683982683982684
22	39129	0.03463203463203463
e
0	34644	0.04047619047619047
1	33769	0.051948051948051945
2	33705	0.05432900432900432
3	33528	0.05476190476190476
4	33650	0.04783549783549783
5	33526	0.19199134199134196
6	33503	0.19956709956709953
7	33927	0.04783549783549783
8	34065	0.04069264069264069
9	33780	0.053896103896103886
10	33687	0.06818181818181818
11	33769	0.04415584415584415
12	33226	0.047402597402597405
13	33412	0.07727272727272728
14	33163	0.06883116883116883
15	33224	0.05974025974025974
16	33341	0.047835497835497835
17	33559	0.19199134199134196
18	33676	0.19956709956709953
19	33821	0.06818181818181818
20	33375	0.047402597402597405
21	33558	0.03376623376623377
22	33436	0.04069264069264069
e
0	34251	0.04177489177489177
1	33259	0.05108225108225108
2	33450	0.05194805194805194
3	33215	0.04696969696969697
4	33463	0.030952380952380953
5	33212	0.17554112554112553
6	33335	0.16969696969696968
7	33816	0.030952380952380953
8	34006	0.04199134199134199
9	36029	0.034848484848484844
10	33987	0.06168831168831169
11	33367	0.012554112554112555
12	33507	0.02359307359307359
13	33347	0.06233766233766234
14	33813	0.06168831168831169
15	33467	0.024025974025974027
16	33490	0.02402597402597402
17	33694	0.17554112554112553
18	33801	0.16969696969696968
19	33797	0.06168831168831169
20	33934	0.02359307359307359
21	33745	0.009740259740259742
22	33767	0.04199134199134199
e

