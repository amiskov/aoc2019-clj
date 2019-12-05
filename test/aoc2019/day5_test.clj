(ns aoc2019.day5-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc2019.utils :as u]
            [aoc2019.day5 :as d]))

(def data (u/file->intcode "day5.txt"))

(deftest part1-test
  (testing ""
    (is (= (d/part1 data)
           3058646))))

(deftest part2-test
  (testing "check `100 * noun + verb` expression"
    (is (= (d/part2 data)
           8976))))

(deftest pos-to-value-test
  (testing ""
    (is (= (d/pos->value [1002 4 3 4 33] 1 1) 4))
    (is (= (d/pos->value [1002 4 3 4 33] 1 0) 33))
    (is (= (d/pos->value [3 3 1107 8 8 3 4 3 99] 4 1) 8))))