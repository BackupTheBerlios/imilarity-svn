set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0.0:0.6]
set y2range [4096:60000]
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
set label 22 "M_{I_3}" at 21.5, graph -0.05, 0 centre norotate
set label 23 "M_{I_{3c}}" at 22.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 12
set output "moments_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.15
plot "-" using ($1+0.24):2 title "rekentijd grijswaarde" linetype 5 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd componenten" linetype 6 axes x1y2,\
	 "-" using ($1+0.76):2 title "rekentijd tralie" linetype 4 axes x1y2,\
 	 "-" using ($1+0.20):3 title "GGGR grijswaarde" linetype 1 axes x1y1,\
         "-" using ($1+0.46):3 title "GGGR componenten" linetype 2 axes x1y1,\
         "-" using ($1+0.72):3 title "GGGR tralie" linetype 3 axes x1y1,\
	 "-" using ($1+0.28):3 title "" linetype 1 axes x1y1,\
         "-" using ($1+0.54):3 title "" linetype 2 axes x1y1,\
         "-" using ($1+0.80):3 title "" linetype 3 axes x1y1

0	31233	0.06558441558441558
1	30258	0.06709956709956709
2	30182	0.06991341991341991
3	30172	0.07207792207792209
4	30220	0.05995670995670996
5	30160	0.11125541125541126
6	30161	0.11883116883116883
7	30243	0.05995670995670996
8	30201	0.06623376623376621
9	30179	0.05865800865800865
10	30246	0.06731601731601732
11	30164	0.35844155844155845
12	30182	0.35
13	30236	0.06017316017316017
14	30201	0.0683982683982684
15	30246	0.36168831168831167
16	30202	0.35108225108225105
17	30258	0.11125541125541126
18	30235	0.11883116883116883
19	30459	0.06731601731601732
20	30458	0.3575757575757576
21	30441	0.05995670995670996
22	30441	0.06623376623376621
e
0	30407	0.0158008658008658
1	30394	0.02683982683982684
2	30407	0.03225108225108225
3	30406	0.0354978354978355
4	30415	0.009307359307359308
5	30451	0.012770562770562773
6	30411	0.027922077922077918
7	30465	0.010173160173160174
8	30520	0.021212121212121213
9	30437	0.008874458874458875
10	30433	0.02034632034632035
11	30405	0.2911255411255411
12	30459	0.27943722943722943
13	30476	0.008441558441558441
14	30526	0.020129870129870133
15	30445	0.29285714285714287
16	30525	0.2826839826839827
17	30230	0.012770562770562773
18	30256	0.027922077922077918
19	30217	0.02034632034632035
20	30297	0.2809523809523809
21	30217	0.010173160173160174
22	30259	0.021212121212121213
e
0	30052	0.01471861471861472
1	30069	0.024458874458874458
2	30131	0.03441558441558441
3	30122	0.0406926406926407
4	30136	0.01038961038961039
5	30110	0.11536796536796536
6	30097	0.12575757575757576
7	30103	0.06731601731601732
8	30099	0.07662337662337662
9	30112	0.07012987012987013
10	30170	0.08008658008658008
11	30128	0.33354978354978354
12	30115	0.3307359307359307
13	30132	0.07142857142857142
14	30108	0.07987012987012988
15	30141	0.33722943722943727
16	30114	0.3309523809523809
17	30305	0.11536796536796536
18	30206	0.12575757575757576
19	30220	0.08008658008658008
20	30292	0.33441558441558433
21	30240	0.06731601731601732
22	30242	0.07662337662337662
e
0	31233	0.06558441558441558
1	30258	0.06709956709956709
2	30182	0.06991341991341991
3	30172	0.07207792207792209
4	30220	0.05995670995670996
5	30160	0.11125541125541126
6	30161	0.11883116883116883
7	30243	0.05995670995670996
8	30201	0.06623376623376621
9	30179	0.05865800865800865
10	30246	0.06731601731601732
11	30164	0.35844155844155845
12	30182	0.35
13	30236	0.06017316017316017
14	30201	0.0683982683982684
15	30246	0.36168831168831167
16	30202	0.35108225108225105
17	30258	0.11125541125541126
18	30235	0.11883116883116883
19	30459	0.06731601731601732
20	30458	0.3575757575757576
21	30441	0.05995670995670996
22	30441	0.06623376623376621
e
0	30407	0.0158008658008658
1	30394	0.02683982683982684
2	30407	0.03225108225108225
3	30406	0.0354978354978355
4	30415	0.009307359307359308
5	30451	0.012770562770562773
6	30411	0.027922077922077918
7	30465	0.010173160173160174
8	30520	0.021212121212121213
9	30437	0.008874458874458875
10	30433	0.02034632034632035
11	30405	0.2911255411255411
12	30459	0.27943722943722943
13	30476	0.008441558441558441
14	30526	0.020129870129870133
15	30445	0.29285714285714287
16	30525	0.2826839826839827
17	30230	0.012770562770562773
18	30256	0.027922077922077918
19	30217	0.02034632034632035
20	30297	0.2809523809523809
21	30217	0.010173160173160174
22	30259	0.021212121212121213
e
0	30052	0.01471861471861472
1	30069	0.024458874458874458
2	30131	0.03441558441558441
3	30122	0.0406926406926407
4	30136	0.01038961038961039
5	30110	0.11536796536796536
6	30097	0.12575757575757576
7	30103	0.06731601731601732
8	30099	0.07662337662337662
9	30112	0.07012987012987013
10	30170	0.08008658008658008
11	30128	0.33354978354978354
12	30115	0.3307359307359307
13	30132	0.07142857142857142
14	30108	0.07987012987012988
15	30141	0.33722943722943727
16	30114	0.3309523809523809
17	30305	0.11536796536796536
18	30206	0.12575757575757576
19	30220	0.08008658008658008
20	30292	0.33441558441558433
21	30240	0.06731601731601732
22	30242	0.07662337662337662
e
0	31233	0.06558441558441558
1	30258	0.06709956709956709
2	30182	0.06991341991341991
3	30172	0.07207792207792209
4	30220	0.05995670995670996
5	30160	0.11125541125541126
6	30161	0.11883116883116883
7	30243	0.05995670995670996
8	30201	0.06623376623376621
9	30179	0.05865800865800865
10	30246	0.06731601731601732
11	30164	0.35844155844155845
12	30182	0.35
13	30236	0.06017316017316017
14	30201	0.0683982683982684
15	30246	0.36168831168831167
16	30202	0.35108225108225105
17	30258	0.11125541125541126
18	30235	0.11883116883116883
19	30459	0.06731601731601732
20	30458	0.3575757575757576
21	30441	0.05995670995670996
22	30441	0.06623376623376621
e
0	30407	0.0158008658008658
1	30394	0.02683982683982684
2	30407	0.03225108225108225
3	30406	0.0354978354978355
4	30415	0.009307359307359308
5	30451	0.012770562770562773
6	30411	0.027922077922077918
7	30465	0.010173160173160174
8	30520	0.021212121212121213
9	30437	0.008874458874458875
10	30433	0.02034632034632035
11	30405	0.2911255411255411
12	30459	0.27943722943722943
13	30476	0.008441558441558441
14	30526	0.020129870129870133
15	30445	0.29285714285714287
16	30525	0.2826839826839827
17	30230	0.012770562770562773
18	30256	0.027922077922077918
19	30217	0.02034632034632035
20	30297	0.2809523809523809
21	30217	0.010173160173160174
22	30259	0.021212121212121213
e
0	30052	0.01471861471861472
1	30069	0.024458874458874458
2	30131	0.03441558441558441
3	30122	0.0406926406926407
4	30136	0.01038961038961039
5	30110	0.11536796536796536
6	30097	0.12575757575757576
7	30103	0.06731601731601732
8	30099	0.07662337662337662
9	30112	0.07012987012987013
10	30170	0.08008658008658008
11	30128	0.33354978354978354
12	30115	0.3307359307359307
13	30132	0.07142857142857142
14	30108	0.07987012987012988
15	30141	0.33722943722943727
16	30114	0.3309523809523809
17	30305	0.11536796536796536
18	30206	0.12575757575757576
19	30220	0.08008658008658008
20	30292	0.33441558441558433
21	30240	0.06731601731601732
22	30242	0.07662337662337662
e
