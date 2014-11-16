(ns clj-website.templates.tags
  (:require [clj-website.core.tags :as tags])
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
