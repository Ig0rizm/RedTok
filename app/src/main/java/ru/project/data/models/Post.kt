package ru.project.data.models

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import ru.project.adapters.RepliesStringJsonAdapter

class Post : ArrayList<Post.PostItem>() {

    data class PostItem(
        val kind: String,
        val `data`: Data
    ) {

        data class Data(
            val after: Any?,
            val dist: Int?,
            val modhash: String,
            @SerializedName("geo_filter")
            val geoFilter: String,
            val children: List<Children>,
            val before: Any?
        ) {

            data class Children(
                val kind: String,
                val `data`: DataX
            ) {

                data class DataX(
                    @SerializedName("approved_at_utc")
                    val approvedAtUtc: Any?,
                    val subreddit: String,
                    val selftext: String?,
                    @SerializedName("user_reports")
                    val userReports: List<Any>?,
                    val saved: Boolean?,
                    @SerializedName("mod_reason_title")
                    val modReasonTitle: Any?,
                    val gilded: Int?,
                    val clicked: Boolean?,
                    val title: String,
                    @SerializedName("link_flair_richtext")
                    val linkFlairRichtext: List<Any>?,
                    @SerializedName("subreddit_name_prefixed")
                    val subredditNamePrefixed: String?,
                    val hidden: Boolean?,
                    val pwls: Int?,
                    @SerializedName("link_flair_css_class")
                    val linkFlairCssClass: Any?,
                    val downs: Int?,
                    @SerializedName("top_awarded_type")
                    val topAwardedType: Any?,
                    @SerializedName("parent_whitelist_status")
                    val parentWhitelistStatus: String?,
                    @SerializedName("hide_score")
                    val hideScore: Boolean?,
                    val name: String,
                    val quarantine: Boolean?,
                    @SerializedName("link_flair_text_color")
                    val linkFlairTextColor: String?,
                    @SerializedName("upvote_ratio")
                    val upvoteRatio: Double?,
                    @SerializedName("author_flair_background_color")
                    val authorFlairBackgroundColor: Any?,
                    @SerializedName("subreddit_type")
                    val subredditType: String?,
                    val ups: Int?,
                    @SerializedName("total_awards_received")
                    val totalAwardsReceived: Int?,
                    @SerializedName("media_embed")
                    val mediaEmbed: MediaEmbed?,
                    @SerializedName("author_flair_template_id")
                    val authorFlairTemplateId: Any?,
                    @SerializedName("is_original_content")
                    val isOriginalContent: Boolean?,
                    @SerializedName("author_fullname")
                    val authorFullname: String?,
                    @SerializedName("secure_media")
                    val secureMedia: Any?,
                    @SerializedName("is_reddit_media_domain")
                    val isRedditMediaDomain: Boolean?,
                    @SerializedName("is_meta")
                    val isMeta: Boolean?,
                    val category: Any?,
                    @SerializedName("secure_media_embed")
                    val secureMediaEmbed: SecureMediaEmbed?,
                    @SerializedName("link_flair_text")
                    val linkFlairText: Any?,
                    @SerializedName("can_mod_post")
                    val canModPost: Boolean?,
                    val score: Int?,
                    @SerializedName("approved_by")
                    val approvedBy: Any?,
                    @SerializedName("is_created_from_ads_ui")
                    val isCreatedFromAdsUi: Boolean?,
                    @SerializedName("author_premium")
                    val authorPremium: Boolean?,
                    val thumbnail: String?,
                    val edited: Any?,
                    @SerializedName("author_flair_css_class")
                    val authorFlairCssClass: Any?,
                    @SerializedName("author_flair_richtext")
                    val authorFlairRichtext: List<Any>?,
                    val gildings: Gildings?,
                    @SerializedName("content_categories")
                    val contentCategories: Any?,
                    @SerializedName("is_self")
                    val isSelf: Boolean?,
                    @SerializedName("mod_note")
                    val modNote: Any?,
                    val created: Double?,
                    @SerializedName("link_flair_type")
                    val linkFlairType: String?,
                    val wls: Int?,
                    @SerializedName("removed_by_category")
                    val removedByCategory: Any?,
                    @SerializedName("banned_by")
                    val bannedBy: Any?,
                    @SerializedName("author_flair_type")
                    val authorFlairType: String?,
                    val domain: String?,
                    @SerializedName("allow_live_comments")
                    val allowLiveComments: Boolean?,
                    @SerializedName("selftext_html")
                    val selftextHtml: String?,
                    val likes: Any?,
                    @SerializedName("suggested_sort")
                    val suggestedSort: Any?,
                    @SerializedName("banned_at_utc")
                    val bannedAtUtc: Any?,
                    @SerializedName("url_overridden_by_dest")
                    val urlOverriddenByDest: String?,
                    @SerializedName("view_count")
                    val viewCount: Any?,
                    val archived: Boolean?,
                    @SerializedName("no_follow")
                    val noFollow: Boolean?,
                    @SerializedName("is_crosspostable")
                    val isCrosspostable: Boolean?,
                    val pinned: Boolean?,
                    @SerializedName("over_18")
                    val over18: Boolean?,
                    @SerializedName("all_awardings")
                    val allAwardings: List<Any>?,
                    val awarders: List<Any>?,
                    @SerializedName("media_only")
                    val mediaOnly: Boolean?,
                    @SerializedName("can_gild")
                    val canGild: Boolean?,
                    val spoiler: Boolean?,
                    val locked: Boolean?,
                    @SerializedName("author_flair_text")
                    val authorFlairText: Any?,
                    @SerializedName("treatment_tags")
                    val treatmentTags: List<Any>?,
                    val visited: Boolean?,
                    @SerializedName("removed_by")
                    val removedBy: Any?,
                    @SerializedName("num_reports")
                    val numReports: Any?,
                    val distinguished: Any?,
                    @SerializedName("subreddit_id")
                    val subredditId: String?,
                    @SerializedName("author_is_blocked")
                    val authorIsBlocked: Boolean?,
                    @SerializedName("mod_reason_by")
                    val modReasonBy: Any?,
                    @SerializedName("removal_reason")
                    val removalReason: Any?,
                    @SerializedName("link_flair_background_color")
                    val linkFlairBackgroundColor: String?,
                    val id: String,
                    @SerializedName("is_robot_indexable")
                    val isRobotIndexable: Boolean?,
                    @SerializedName("num_duplicates")
                    val numDuplicates: Int?,
                    @SerializedName("report_reasons")
                    val reportReasons: Any?,
                    val author: String?,
                    @SerializedName("discussion_type")
                    val discussionType: Any?,
                    @SerializedName("num_comments")
                    val numComments: Int?,
                    @SerializedName("send_replies")
                    val sendReplies: Boolean?,
                    val media: Any?,
                    @SerializedName("contest_mode")
                    val contestMode: Boolean?,
                    @SerializedName("author_patreon_flair")
                    val authorPatreonFlair: Boolean?,
                    @SerializedName("author_flair_text_color")
                    val authorFlairTextColor: Any?,
                    val permalink: String?,
                    @SerializedName("whitelist_status")
                    val whitelistStatus: String?,
                    val stickied: Boolean?,
                    val url: String?,
                    @SerializedName("subreddit_subscribers")
                    val subredditSubscribers: Int?,
                    @SerializedName("created_utc")
                    val createdUtc: Double?,
                    @SerializedName("num_crossposts")
                    val numCrossposts: Int?,
                    @SerializedName("mod_reports")
                    val modReports: List<Any>?,
                    @SerializedName("is_video")
                    val isVideo: Boolean?,
                    @SerializedName("comment_type")
                    val commentType: Any?,
                    @JsonAdapter(RepliesStringJsonAdapter::class)
                    val replies: Any?,
                    @SerializedName("collapsed_reason_code")
                    val collapsedReasonCode: Any?,
                    @SerializedName("parent_id")
                    val parentId: String?,
                    val collapsed: Boolean?,
                    val body: String?,
                    @SerializedName("is_submitter")
                    val isSubmitter: Boolean?,
                    @SerializedName("body_html")
                    val bodyHtml: String?,
                    @SerializedName("collapsed_reason")
                    val collapsedReason: Any?,
                    @SerializedName("associated_award")
                    val associatedAward: Any?,
                    @SerializedName("unrepliable_reason")
                    val unrepliableReason: Any?,
                    @SerializedName("score_hidden")
                    val scoreHidden: Boolean?,
                    @SerializedName("link_id")
                    val linkId: String?,
                    val controversiality: Int?,
                    val depth: Int?,
                    @SerializedName("collapsed_because_crowd_control")
                    val collapsedBecauseCrowdControl: Any?,
                    val count: Int?,
                    val children: List<String>?
                ) {

                    data class Replies(
                        val kind: String,
                        val data: Data
                    )

                    class MediaEmbed

                    class SecureMediaEmbed

                    class Gildings
                }
            }
        }
    }
}