(ns sketch.core  (:require [quil.core :as q]
                           [sketch.dynamic :as dynamic])
    (:gen-class))

(def sketch)

(q/defsketch sketch
  :title "sketch"
  :setup dynamic/initialise
  :draw dynamic/draw
  :features [:keep-on-top]
  :size [900 900])

(defn refresh
  "Refresh the sketch."
  []
  (use :reload 'sketch.dynamic)
  (.loop sketch))
