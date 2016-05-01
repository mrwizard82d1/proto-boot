(deftask what
  "Specify a thing (and whether or not its plural)."
  [t thing THING str "Specify a thing"
   p pluralize bool "And whether or not it is plural"]
  (fn middleware [next-handler]
    (fn handler [_]
      (next-handler {:thing thing :pluralize pluralize}))))

(deftask fire
  "Announces that something is on fire."
  []
  (fn middleware [next-handler]
    (fn handler [thing-map]
      (let [updated-thing-map (next-handler thing-map)
            verb (if (:pluralize updated-thing-map) "are" "is")]
        (println "My" (:thing updated-thing-map) verb "on fire!")))))

(deftask foo
  "Fooey"
  [f foo FOO=BAR:BAZ #{[kw sym str]} "The foo options."]
  *opts*)
