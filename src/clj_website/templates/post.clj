(ns clj-website.templates.post
  (:require [clj-website.templates.layout :as layout]
            [clj-website.templates.tags :as tag-templates])
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5)]))

(def post-index-header
  (html
    [:h6 {:class "info"}
     [:strong "posts"]
     " | "
     [:a {:href "/tags"}]]))

(defn post-header
  [date md-file]
  (html
    [:h6 {:class "info"}
     "posted: "
     [:strong date]
     " | "
     [:a {:href md-file} "MD"]]))

(defn index-item
  [post]
  (let [date (:date post)
        slug (:slug post)
        md-file (:file post)
        title (:title post)
        description (:description post)]
    (html
      (post-header date md-file)
      [:h2 {:class "post-index-title"} 
       [:a {:href (str "/" slug)} title]]
      [:p {:class "post-index-description"} description])))

(defn index
  [posts]
  (layout/common
    (html
      post-index-header
      (map index-item (reverse (sort-by :date posts))))))

(defn post-footer
  [tags]
  (html
    [:h6 {:class "info"}
     "tags: "
     (apply tag-templates/tag-link-list tags)]))

(defn post
  [data body]
  (let [date (:date data)
        md-file (:file data)
        title (:title data)
        tags (:tags data)]
    (layout/common title 
                   (html
                     (post-header date md-file)
                     body
                     (post-footer tags)))))
