(ns clj-website.core.posts
  (:require [clojure.tools.macro :as macro]
            [clj-yaml.core :as yaml]))

(defn process-post-indexes
  [& indexes]
  (reduce 
    (fn 
      [index index-file]
      (let [index-contents (yaml/parse-string (slurp index-file))]
        (concat index index-contents)))
    []
    indexes))

(def post-index
  (process-post-indexes "me/blog/post-index.yml"))

(defn get-file
  [post]
  (str "me/blog/posts/" (:file post)))
