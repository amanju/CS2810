#!/bin/bash
# CS2810 Testbench
# Runs tests

TEST_DIR=../tests
TEST_COUNT=5
PROG="java NearestPrime"
AS_ARGS=0
if [[ $1 = "-g" ]]; then GEN=1; else GEN=0; fi;

cd bin;
j=0
for i in `seq 1 $TEST_COUNT`; do
    echo -n "Running Test $i... "
    inp="in-$i";
    outp="out-$i";
    outp_="out-${i}_";
    if [[ $AS_ARGS == 1 ]]; then
      $PROG $ARGS `cat $TEST_DIR/$inp` > $TEST_DIR/$outp_;
    else 
      $PROG $ARGS < $TEST_DIR/$inp > $TEST_DIR/$outp_;
    fi
    if [[ $GEN != 0 ]]; then
      mv $TEST_DIR/$outp_ $TEST_DIR/$outp;
      echo "Generated"
    else
      diff -bBq $TEST_DIR/$outp $TEST_DIR/$outp_ > /dev/null;
      if [[ $? == 0 ]] ; then
          echo "Passed."
          j=$((j+1))
      else
          echo "Failed."
      fi
    fi
done;
echo "Done ($j/$i) passed."
cd ..;

