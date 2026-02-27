package gsoc
package contributors

import cats.effect.*

import fs2.concurrent.*
import fs2.dom.HtmlElement

import calico.html.io.{*, given}
import calico.syntax.*

val abby_ql: Contributor = Contributor("abby-ql"):
  SignallingRef[IO].of(false).toResource.flatMap { revealed =>
    div(
      p(
        "Hi I'm ",
        revealed.map {
          case false => "black"
          case true  => "rainbow"
        }.changes.map { mode =>
          if mode == "black" then
            span(
              styleAttr := "font-weight: bold;",
              "@abby-ql"
            )
          else
            span(
              span(styleAttr := "color: violet; font-weight: bold;", "@"),
              span(styleAttr := "color: red; font-weight: bold;", "a"),
              span(styleAttr := "color: red; font-weight: bold;", "b"),
              span(styleAttr := "color: orange; font-weight: bold;", "b"),
              span(styleAttr := "color: orange; font-weight: bold;", "y"),
              span(styleAttr := "color: yellow; font-weight: bold;", "-"),
              span(styleAttr := "color: green; font-weight: bold;", "q"),
              span(styleAttr := "color: blue; font-weight: bold;", "l")
            )
        },
        " on GitHub. I agree to follow the Typelevel CoC and GSoC AI policy."
      ),

      revealed.map(r =>
        if r then
          p(
            span(styleAttr := "color: red; font-weight: bold;", "I "),
            span(styleAttr := "color: orange; font-weight: bold;", "am "),
            span(styleAttr := "color: yellow; font-weight: bold;", "excited "),
            span(styleAttr := "color: green; font-weight: bold;", "to "),
            span(styleAttr := "color: blue; font-weight: bold;", "contribute "),
            span(styleAttr := "color: indigo; font-weight: bold;", "to "),
            span(styleAttr := "color: violet; font-weight: bold;", "Typelevel!")
          )
        else div("")
      ),

      button(
        onClick --> (_.foreach(_ => revealed.update(!_))),
        revealed.map(r =>
          if r then "Hide rainbow :)"
          else "Click to reveal rainbow :)"
        )
      )
    )
  }