package eu.kanade.tachiyomi.extension.th.upmanga

import eu.kanade.tachiyomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class UpManga : MangaThemesia(
    name = "Up-Manga",
    baseUrl = "https://www.up-manga.com",
    lang = "th",
    // Chapter dates on the manga page look like: "มิถุนายน 30, 2026"
    // (Thai month name, day, comma, year - already Gregorian, not Buddhist Era).
    dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
) {

    // The base class's default keyword lists for Author/Artist are English/Spanish/etc.
    // and don't include the Thai labels used on this site's info table
    // (see example on /awakening-the-purple/ : "ผู้แต่ง" and "ผู้เขียน").
    // Adding them here so those two fields get filled in correctly.
    // NOTE: not 100% certain which Thai label maps to "author" vs "artist" on this
    // theme - if they come out swapped once you test it, just swap the two lines below.
    override val seriesAuthorSelector = ".infotable tr:contains(ผู้แต่ง) td:last-child, " +
        super.seriesAuthorSelector

    override val seriesArtistSelector = ".infotable tr:contains(ผู้เขียน) td:last-child, " +
        super.seriesArtistSelector

    // Everything else (popular/latest/search listing, manga details, status,
    // genres, chapter list, and reading pages) is already handled correctly by
    // the MangaThemesia base class using its default selectors - confirmed by
    // checking this site's actual HTML structure (it uses the stock "MangaThemesia"
    // WordPress theme without custom class names).
}
