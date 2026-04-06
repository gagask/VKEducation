package com.example.vkeducation.domain

import com.example.vkeducation.R

enum class AppType(
    val titleResId: Int
) {
    FINANCE(R.string.type_finance),
    TOOLS(R.string.type_tools),
    TRANSPORT(R.string.type_transport);
}