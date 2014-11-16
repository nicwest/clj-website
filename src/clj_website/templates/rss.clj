(ns clj-website.templates.rss
  (:require [ring.util.response :as response]
            [clj-website.core.posts :as posts])
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (xml-declaration)]
        [clj-website.views.blog :only (read-post-as-html)]))

(defn format-timestamp
  [timestamp]
  (str "2014-11-16T18:30:00Z"))

(defn feed-item
  [post]
  (html
    [:entry
     [:title (:title post)]
     [:link {:href (str "http://nic-west.com/" (:slug post))}]
     [:id (str "http://nic-west.com/" (:slug post))]
     [:updated (format-timestamp (:date post))]
     [:summary (:description post)]
     [:content {:type "xhtml"} (read-post-as-html post)]
     [:author 
      [:name "Nic West"]
      [:email "info@nic-west.com"]]]))

(def feed-content
  (str
  (xml-declaration "utf-8")
  (html
    [:feed {:xmlns "http://www.w3.org/2005/Atom"}
     [:title "Nic West - Blog"]
     [:link {:href "http://nic-west.com/feed/" :ref "self"}]
     [:link {:href "http://nic-west.com"}]
     [:updated (format-timestamp "foo")]
     (map feed-item posts/post-index)])))

(defn feed
  [req]
  (-> (response/response feed-content)
      (response/header "Content-Type" "application/atom+xml; charset=utf-8")))
