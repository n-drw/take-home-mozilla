/**
 * NOTE: You're not expected to edit this file as part of your solution. But you're welcome to read it!
 * 
 * This file contains just some fakes to setup a mocked data loading process from an imaginary
 * Pocket server. This is not meant to look anything like real code.
 * 
 * If you feel really inclined to fix or refactor something in here, please add some comments
 * explaining what you'd do (but again, you're not really expected to do so, it's not part
 * of the exercise).
 */
package com.pocket.homework.internal

import android.os.SystemClock
import kotlin.math.max
import kotlin.random.Random
import kotlin.random.nextLong

/**
 * **DO NOT MODIFY THIS CLASS.**
 * 
 * A fake-ish (simplified) item model. Items are articles and any other urls people save to their
 * lists in Pocket.
 */
class Item(
    val url: String,
    val title: String,
    val excerpt: String,
    val publishedAt: Long,
    val author: String?,
    val thumbnailUrl: String?,
)

/**
 * **DO NOT MODIFY THIS CLASS.**
 * 
 * Fake repository of [Item]s. Imagine this is a real thing, that sends a request to a Pocket
 * server, complete with some authorization or token and retrieves [Item]s saved by the currently
 * logged-in person.
 */
class ItemRepository {
    fun fetch(): List<Item> {
        // I'm not sleeping, I'm fetching the data over the network, okay?
        SystemClock.sleep(Random.nextLong(500L..1500L))

        return listOf(
            Item(
                "https://www.wired.com/story/nfts-hot-effect-earth-climate/",
                "NFTs Are Hot. So Is Their Effect on the Earth’s Climate",
                "The sale of a piece of crypto art consumed as much energy as the studio uses in two years. Now the artist is campaigning to reduce the medium’s carbon emissions.",
                1615029008,
                "Gregory Barber",
                "https://pocket-syndicated-images.s3.amazonaws.com/606c76ead8e8c.png"
            ),
            Item(
                "https://www.vice.com/en/article/pkdj79/peoples-expensive-nfts-keep-vanishing-this-is-why",
                "People’s Expensive NFTs Keep Vanishing. This Is Why",
                "“There was no history of my ever purchasing it, or ever owning it,” said one confused NFT buyer. “Now there’s nothing. My money’s gone.”",
                1617016009,
                "Ben Munster",
                "https://video-images.vice.com/articles/6061f183a343c40099034582/lede/1617032454664-gettyimages-526509931.jpeg?crop=1xw:0.8416xh;0xw,0.0926xh"
            ),
            Item(
                "https://www.theatlantic.com/ideas/archive/2021/04/nfts-werent-supposed-end-like/618488/",
                "NFTs Weren’t Supposed to End Like This",
                "When we invented non-fungible tokens, we were trying to protect artists. But tech-world opportunism has struck again.",
                1617362010,
                "Anil Dash",
                "https://pocket-syndicated-images.s3.amazonaws.com/606c773783da9.jpg"
            ),
            Item(
                "https://www.washingtonpost.com/world/2021/03/25/faq-suez-canal-ever-given/",
                "How Did a Ship Get Stuck in the Suez Canal?",
                "A sandstorm reportedly hit the more than 1,300-foot Ever Given, decreasing visibility and battering the ship with heavy winds.",
                1617189000,
                "Miriam Berger, Júlia Ledur and Adam Taylor",
                "https://www.washingtonpost.com/wp-stat/graphics/ai2html/suez-satellite/ZNPWVXH5ONE3NDRVKG3PJZBXLI/satellite-map-Artboard_57.jpg?v=10"
            ),
            Item(
                "https://www.wired.com/story/what-take-cargo-ship-un-stuck-suez-canal/",
                "The Wild Logistics of Getting a Giant Cargo Ship Out of the Suez Canal",
                "The Ever Given is stuck in one of the world’s most vital waterways. This is how the vessel – and global trade – can be salvaged. ",
                1616584001,
                "Alex Christian",
                "https://pocket-syndicated-images.s3.amazonaws.com/605dff9c82f71.jpg"
            ),
            Item(
                "https://www.bloomberg.com/news/articles/2021-03-26/what-is-the-suez-canal-and-why-is-it-so-important-quicktake?srnd=premium&sref=a2d7LMhq",
                "Why a Canal Built in 1869 is More Important Than Ever",
                "About 12 percent of world trade passes through the canal each year, everything from crude oil to grains to instant coffee.",
                1616757002,
                "Robert Tuttle",
                "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iWaxYCPrFV.k/v0/-1x-1.jpg"
            ),
            Item(
                "https://www.nytimes.com/interactive/2020/08/06/magazine/fashion-sweatpants.html",
                "Sweatpants Forever",
                "Even before the pandemic, the whole fashion industry\n" +
                        "had started to unravel. What happens now that no one\n" +
                        "has a reason to dress up?",
                1596716605,
                "Irina Aleksander",
                "https://static01.nyt.com/newsgraphics/2020/08/02/entireworld/assets/images/topper-2000_x2.jpg"
            ),
            Item(
                "https://www.nationalgeographic.com/science/article/coronavirus-zoom-fatigue-is-taxing-the-brain-here-is-why-that-happens",
                "‘Zoom fatigue’ is taxing the brain. Here's why that happens.",
                "Video calls seemed an elegant solution to remote work, but they wear on the psyche in complicated ways.",
                1587731004,
                "Julia Sklar",
                "https://i.natgeofe.com/n/18abc608-800d-4c23-82b8-73eaf1d66470/zoom_calls_065.jpg?w=2880&h=3600"
            ),
            Item(
                "https://www.vox.com/the-goods/22358262/value-of-nfts-behavioral-expert",
                "The Value of NFTs, Explained by an Expert",
                "How emotional attachment to certain items and gifts could affect our understanding of value.",
                1617189003,
                "Terry Nguyen",
                "https://cdn.vox-cdn.com/thumbor/yO_l9UblVn7S8SdsPiiQeAwaDJ8=/0x0:4707x3138/1820x1213/filters:focal(1978x1193:2730x1945):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/69053624/GettyImages_1231940859.0.jpg"
            ),
            Item(
                "https://www.newyorker.com/tech/annals-of-technology/how-beeple-crashed-the-art-world",
                "How Beeple Crashed the Art World",
                "An N.F.T., or “non-fungible token,” of the digital artist’s work sold for sixty-nine million dollars in a Christie’s auction. It’s good news for crypto-optimists, but what about for art?",
                1616411004,
                "Kyle Chayka",
                "https://pocket-syndicated-images.s3.amazonaws.com/606c76378904d.jpg"
            ),
            Item(
                "https://popula.com/2021/03/12/beeples-nft-sale/",
                "The Art World Was Broken Before NFTs",
                "The art world, not the crypto world, is running this thing.",
                1615547005,
                "Maria Bustillos",
                "https://i1.wp.com/popula.com/wp-content/uploads/2021/03/beep.png?resize=2880,1620"
            ),
            Item(
                "https://www.nytimes.com/2021/03/24/technology/nft-column-blockchain.html",
                "Buy This Column on the Blockchain!",
                "Why can’t a journalist join the NFT party, too?",
                1618312007,
                "Kevin Roose",
                "https://static01.nyt.com/images/2021/03/24/business/24roose/00roose-superJumbo.jpg?quality=99"
            ),
            // REVIEW: Some of these images seem rather large, is that going to affect scrolling performance? 
        )
            .drop(max(0, 3 - numberOfTimesFetchedSoWeCanSimulateUpdatingData))
            .also {
                numberOfTimesFetchedSoWeCanSimulateUpdatingData++
            }
    }

    /**
     * Imagine this is not a hacky counter, instead it's someone updating their list from another
     * client, so that when you refresh the list, you see an updated state.
     */
    private var numberOfTimesFetchedSoWeCanSimulateUpdatingData = 0
}
