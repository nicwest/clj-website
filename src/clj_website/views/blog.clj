(ns clj-website.views.blog
  (:require [clj-website.core.posts :as posts]
            [clj-website.core.tags :as tags]
            [clj-website.templates.post :as post-templates]
            [clj-website.templates.tags :as tags-templates]
            [clojure.string :as string])
  (:use [markdown.core :only (md-to-html-string)]))

(defn get-post
  [slug]
  (first
    (filter 
      (fn [post]
        (= (:slug post) slug))
      posts/post-index)))

(defn read-post-as-html
  [post]
  (string/replace
    (md-to-html-string (slurp (posts/get-file post)))
    #"<pre><code(.*)>\n" "<pre><code$1>"))

(defn index
  []
  (post-templates/index posts/post-index))

(defn post
  [slug]
  (let [post (get-post slug)]
    (if post
      (post-templates/post post (read-post-as-html post))
      (str "NOPE :("))))

(defn tags
  []
  (tags-templates/index ))

(defn tag
  [tag]
  (post-templates/index (posts/filter-by-tag tag)))
