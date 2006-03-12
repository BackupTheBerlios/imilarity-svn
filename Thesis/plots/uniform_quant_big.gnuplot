set encoding iso_8859_1
set data style boxes
set size 0.9,0.7
set grid

set yrange [0:5000]
set ylabel "rekentijd in ms" 1,0

set xrange [0:8]
set noxtics
set label 1 "HSV" at 0.5, graph -0.05, 0 centre norotate
set label 2 "I1I2I3" at 1.5, graph -0.05, 0 centre norotate
set label 3 "Irb" at 2.5, graph -0.05, 0 centre norotate
set label 4 "XYZ" at 3.5, graph -0.05, 0 centre norotate
set label 5 "Yxy" at 4.5, graph -0.05, 0 centre norotate
set label 6 "L*a*b*" at 5.5, graph -0.05, 0 centre norotate
set label 7 "focal" at 6.5, graph -0.05, 0 centre norotate
set label 8 "SCT" at 7.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 20
set output "uniform_quant_big.eps"

set key left top
set boxwidth 0.40
plot "-" using ($1+0.50):2 title "" linetype 1 axes x1y1

0 1221
1 1145
2 1142
3 1112
4 1295
5 3906
6 4462
7 1229
e


