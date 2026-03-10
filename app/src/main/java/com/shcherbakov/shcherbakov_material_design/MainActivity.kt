package com.shcherbakov.shcherbakov_material_design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shcherbakov.shcherbakov_material_design.data.Student
import com.shcherbakov.shcherbakov_material_design.data.students
import com.shcherbakov.shcherbakov_material_design.ui.theme.Shcherbakov_Material_DesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Shcherbakov_Material_DesignTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentApp()
                }
            }
        }
    }
}

@Composable
fun StudentIcon(@DrawableRes studentIcon: Int) {
    Image(
        painter = painterResource(id = studentIcon),
        contentDescription = null,
        modifier = Modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun StudentInformation(@StringRes studentName: Int, studentAge: Int) {
    Column {
        Text(
            text = stringResource(studentName),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(R.string.years_old, studentAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun StudentItem(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StudentIcon(student.imageResourceId)
            StudentInformation(student.name, student.age)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_student_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun StudentApp() {
    Scaffold(
        topBar = { StudentTopAppBar() }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        ) {
            items(students) { student ->
                StudentItem(student)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentPreview() {
    Shcherbakov_Material_DesignTheme(darkTheme = false) {
        StudentApp()
    }
}

@Preview(showBackground = true)
@Composable
fun StudentDarkThemePreview() {
    Shcherbakov_Material_DesignTheme(darkTheme = true) {
        StudentApp()
    }
}