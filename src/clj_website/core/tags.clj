(ns clj-website.core.tags
  (:require [clj-yaml.core :as yaml]))

(defn process-important-tags
  [& tag-files]
  (reduce 
    (fn 
      [important-tags tag-file]
      (let [tag-file-contents (yaml/parse-string (slurp tag-file))]
        (concat important-tags tag-file-contents)))
    []
    tag-files))

(def important
  (process-important-tags "me/blog/important-tags.yml"))

(defn important?
  [tag]
  (some #(= tag %) important))
