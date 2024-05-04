package com.example.superherolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.superherolist.data.Hero
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superherolist.data.heroes
import com.example.superherolist.ui.theme.SuperheroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroApp()
                }
            }
        }
    }
}
@Composable
fun SuperheroApp() {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(heroes) {
                SuperheroItem(
                    hero = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun SuperheroItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard (elevation = CardDefaults.cardElevation(
        defaultElevation = 5.dp
    ),
        modifier = modifier
        ) {
        Column (
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
            ) {
                SuperheroIcon(superheroIcon = hero.imageRes)
                SuperheroInfo(superheroName = hero.nameRes, superheroAge = hero.age)
                Spacer(modifier = Modifier.weight(1f))
                SuperheroItemButton(expanded = expanded, onClick = {expanded = !expanded})
            }
            if (expanded){
                SuperheroDesc(superheroDesc = hero.descriptionRes,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun SuperheroIcon(
    @DrawableRes superheroIcon: Int,
    modifier: Modifier = Modifier
){
    Image(modifier = modifier
        .size(dimensionResource(id = R.dimen.image_size))
        .padding(dimensionResource(id = R.dimen.padding_small))
        .clip(MaterialTheme.shapes.extraLarge),
        painter = painterResource(id = superheroIcon), contentDescription = null
    )
}

@Composable
fun SuperheroInfo(
    @StringRes superheroName: Int,
    superheroAge: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(id = superheroName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)))
        Text(
            text = stringResource(R.string.years_old, superheroAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SuperheroItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun SuperheroDesc(
    @StringRes superheroDesc: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.labelSmall)
        Text(text = stringResource(id = superheroDesc),
            style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size)),
                    painter = painterResource(id = R.drawable.superherologo),
                    contentDescription = null)
                Text(text = stringResource(id = R.string.top_app_bar_name),
                    fontSize = 39.sp,
                    fontFamily = FontFamily(Font(R.font.abrilfatface_regular))
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SuperheroTheme {
        SuperheroApp()
    }
}
@Preview
@Composable
fun AppDarkPreview() {
    SuperheroTheme (darkTheme = true){
        SuperheroApp()
    }
}