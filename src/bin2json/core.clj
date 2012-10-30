(ns bin2json.core
  (:require [cheshire.core :as JSON])
  (:use [clojure.java.shell :only [sh]]))

(comment
  bin2json.core=>(println (bin2json "echo" 1 2 3))
;  {"_cmd":"echo 1 2 3",
;   "_data":{"exit":0,
;            "out":"1 2 3\n",
;            "err":""}}
  )

(defn bin2json
  [program & args]
  (let
      [args (reduce
             #(clojure.string/join " " %&)
             args)]

  (JSON/generate-string
   (hash-map :_cmd (str program " " args)
             :_data (sh program args))
   {:escape-non-ascii true})))

                