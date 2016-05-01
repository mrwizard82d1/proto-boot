(ns comp-n-coord)

(def strinc (comp str inc))

(strinc 3)

(defn whiney-str
  [rejects]
  {:pre [(set? rejects)]}
  (fn [x]
    (if (rejects x)
      (str "I don't like " x)
      (str x))))

(def whine (whiney-str #{2}))

(whine 3)

(whine 2)

(def whiney-str-inc (comp (whiney-str #{2}) inc))

(whiney-str-inc 3)

(whiney-str-inc 1)

(defn whiney-middleware
  [next-handler rejects]
  {:pre [(set? rejects)]}
  (fn [x]
    (if (= x 1)
      "No. I will not do that."
      (let [y (next-handler x)]
        (if (rejects y)
          (str "I don't like " y)
          (str y))))))

(def whiney-str-inc-wm (whiney-middleware inc #{2}))

(whiney-str-inc-wm 2)

(whiney-str-inc-wm 1)

(defn whiney-middleware-factory
  [rejects]
  {:pre [(set? rejects)]}
  (fn [handler]
    (fn [x]
      (if (= x 1)
        (str "I'm still not doing that.")
        (let [y (handler x)]
          (if (rejects y)
            (str "I don't like green eggs and " y)
            (str y)))))))

(def whiney-str-inc-mwf ((whiney-middleware-factory #{2}) inc))

(whiney-str-inc-mwf 2)
