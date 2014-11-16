(ns clj-website.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clj-website.templates.rss :as rss]
            [clj-website.views.blog :as blog]))

(defroutes app-routes
  (GET "/" [] (blog/index))
  (GET "/feed.atom" [] rss/feed)
  (GET "/:slug" [slug] (blog/post slug))
  (route/files "/static/blog/images/" {:root "me/blog/images/"})
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
