(ns clj-website.core.tags
  (:require [clj-yaml.core :as yaml]
            [clj-website.core.posts :as posts]))

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

(def all
  (reduce 
    (fn 
      [tags post]
      (let [post-tags (:tags post)]
        (into [] (sort (set (concat tags post-tags))))))
    []
    posts/post-index))
