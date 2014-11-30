(ns clj-website.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clj-website.templates.rss :as rss]
            [clj-website.views.blog :as blog]))

(defroutes app-routes
  (GET "/" [] (blog/index))
  (GET "/feed.atom" [] rss/feed)
  (GET "/tags" [] (blog/tags))
  (GET "/tags/:tag" [tag] (blog/tag tag))
  (GET "/:slug" [slug] (blog/post slug))
  (route/files "/static/blog/images/" {:root "me/blog/images/"})
  (route/not-found "Not Found"))

;https://gist.github.com/dannypurcell/8215411
(defn ignore-trailing-slash
  [handler]
  (fn [request]
    (let [uri (:uri request)]
      (handler (assoc request :uri (if (and (not (= "/" uri))
                                            (.endsWith uri "/"))
                                     (subs uri 0 (dec (count uri)))
                                     uri))))))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      (ignore-trailing-slash)))
