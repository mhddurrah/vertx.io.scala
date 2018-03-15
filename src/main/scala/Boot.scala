import io.vertx.core.json.JsonObject
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.mongo.MongoClient
import io.vertx.scala.ext.web.Router

import scala.util.{Failure, Success}

/**
  * Created by durrah on 3/9/18.
  */
object Boot extends App {

  val vertx = Vertx.vertx()

  var server = vertx.createHttpServer()

  val mongoClient = MongoClient.createShared(vertx, new JsonObject().put("db_name", "university"))

  import scala.concurrent.ExecutionContext.Implicits.global

  val router = Router.router(vertx)

  router.route().handler(routingContext => {
    val request = routingContext.request()
    val document = new JsonObject().put("name", "John Smith")

    mongoClient.saveFuture("students", document).onComplete {
      case Success(result) =>
        request.response().end(s"saved with id: $result")

      case Failure(cause) =>
        request.response().end(s"an error occured, reason: $cause")

    }
  })

  server.requestHandler(router.accept _).listen(8081)
}
