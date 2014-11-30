(ns clj-website.templates.tags
  (:require [clj-website.templates.layout :as layout]
            [clj-website.core.tags :as tags])
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5)]))

(defn tag-link
  [tag]
  (html 
    [:a {:href (str "/tags/" tag)}
     (if (tags/important? tag)
       [:strong tag]
       tag)]))

(defn tag-link-list
  [& tags]
  (html
    (reduce
      (fn [tag-links tag]
        (if (empty? tag-links)
          (tag-link tag)
          (concat tag-links [", " (tag-link tag)])))
      []
      tags)))


(def tag-index-header
  (html
    [:h6 {:class "info"}
     [:a {:href "/"} "posts"]
     " | "
     [:strong "tags"]]))

(defn index
  []
  (layout/common 
    "tags"
    (html
      tag-index-header
      [:p {:class "tag-index"}
       (apply tag-link-list tags/all)])))
