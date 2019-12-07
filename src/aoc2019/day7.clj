(ns aoc2019.day7
  (:require [clojure.math.combinatorics :as combo]
            [aoc2019.utils :as u]
            [clojure.tools.trace :as t]
            [aoc2019.day5 :as d5]))

(defmethod d5/evaluate 3 [{pos :pos prg :program input :input}]
  {:program (assoc prg (prg (inc pos)) (first input))
   :input (rest input)
   :pos (+ 2 pos)})

(defn prepare-memory [intcode input phase]
  {:pos 0
   :halt false
   :program intcode
   :input [phase input]
   :out []})

(defn run-amps-once [intcode phases]
  (reduce
   (fn [inp phase]
     (last (:out (d5/execute (prepare-memory intcode inp phase)))))
   0
   phases))

(defn part1 [intcode]
  (reduce max (map
               (fn [phases] (run-amps-once intcode phases))
               (combo/permutations [0 1 2 3 4]))))

;; Tests
(= (part1  [3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0]) 43210)
(= (part1 [3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0]) 54321)
(= (part1 [3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0]) 65210)

;; Part 1 Solution
(def puzzle-intcode (u/file->intcode "day7.txt"))
(= (part1 puzzle-intcode) 116680)


; 1. make pipeline of computers (amplifiers)
; 2. give the pipeline phases and input for the first computer
; 3. pipeline will produce output


; Part2


;; Tests
(def p2t1 [3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26, 27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5])
; ans: 139629729, [9,8,7,6,5]

(def p2t2 [3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54, -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10])
; ans: 18216 [9,7,8,5,6]