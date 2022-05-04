(ns sketch.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]
            [colours]))

(defn save-frame-to-disk
  ([]
   (q/save-frame (pp/cl-format nil
                               "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                               (q/year) (q/month) (q/day)
                               (q/hour) (q/minute) (q/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- width-proportion
  "A proportion of the frame width."
  [p]
  (* (q/width)
     p))

(defn- height-proportion
  "A proportion of the frame height."
  [p]
  (* (q/height)
     p))

(defn- draw-point
  "Create a point in the circles."
  []
  (let [f (* 75
             (q/random-gaussian))
        [x y] (q/random-2d)]
    (q/ellipse (* x f)
               (* y f)
               3
               3)))

(defn- draw-points
  "Create N points in the circles."
  [n]
  (apply q/fill colours/red)
  (apply q/stroke colours/red)
  (q/with-translation [(width-proportion (q/random 1.0))
                       (height-proportion (q/random 1.0))]
    (dotimes [_ n]
      (draw-point))))

(defn- draw-circles
  "Draw N circles."
  [n]
  (q/with-translation [(width-proportion 0.5)
                       (height-proportion 0.5)]
    (dotimes [r n]
      (q/stroke 180 9 63 (q/random 1.0))
      (q/ellipse (q/random -5 5) (q/random -5 5) r r))))

(defn draw
  []
  (q/no-loop)
  (apply q/background colours/base3)
  (q/no-fill)
  (draw-circles 535)
  (draw-points 275)
  (save-frame-to-disk))

(defn initialise
  []
  (q/smooth)
  (q/color-mode :hsb 360 100 100 1.0))