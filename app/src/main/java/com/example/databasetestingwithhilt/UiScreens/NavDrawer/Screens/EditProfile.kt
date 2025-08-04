package com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens

import androidx.appcompat.widget.DialogTitle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavController,firebaseViewmodel: FirebaseViewmodel){

    val data by firebaseViewmodel.personalData.collectAsState()
    var alertDialogBoxState = remember { mutableStateOf(false) }
    var infoDialogBoxState = remember { mutableStateOf(false) }
    var selectedField = remember { mutableStateOf<String?>(null) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 22.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                  IconButton(
                     onClick = {navController.navigate("NavigationDrawerScreen")}
                  ) {
                      Icon(
                          imageVector = Icons.Filled.ArrowBack,
                          contentDescription = null
                      )
                  }
                }
            )
        }
    ){innerPadding ->
        Column (modifier = Modifier.fillMaxSize().padding(innerPadding)){

            val fields = listOf("name","height","weight","age","gender","goal","occupationType","activityLevel","exerciseFrequency")

            fields.forEach { items->
                profileItem(
                    buttonTitle = items.replaceFirstChar { it.uppercaseChar() },
                    data = data,
                    onClick = {
                        selectedField.value=items
                        alertDialogBoxState.value=true
                    },
                    key = items
                )
            }
        }

        if (alertDialogBoxState.value){
            AlertDialogBox(
                showDialog =
                alertDialogBoxState.value,
                onDismiss = {
                            alertDialogBoxState.value=false
                            selectedField.value=null
                            },
                onConfirm = {
                            infoDialogBoxState.value= true
                            alertDialogBoxState.value=false
                }

            )
        }

        if (infoDialogBoxState.value && selectedField.value != null){
            CustomTextDialogBox(
               key = selectedField.value!!,
                currentValue = data[selectedField.value]?.toString() ?: "",
                onDismiss = {
                    infoDialogBoxState.value=false
                    selectedField.value=null
                },
                onSave = {

                    infoDialogBoxState.value=false
                    selectedField.value=null
                }
            )
        }
    }
}

@Composable
fun profileItem(buttonTitle:String,
                data:Map<String, Any?>,
                onClick: () -> Unit,
                key:String
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
            ,verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = buttonTitle,
                style = TextStyle(
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)

            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${data.getValue(key)}",
                style = TextStyle(
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = DarkBlue
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
                .padding(top= 12.dp)
        )
    }
}
@Composable
fun AlertDialogBox(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,

) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = "Are you sure?", fontWeight = FontWeight.Bold)
            },
            text = {
                Text(text = "This will recalculate your calories and nutrient goals which will overwrite any customer settings. Would you like to continue?")
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(16.dp)
        )
    }
}
@Composable
fun CustomTextDialogBox(
    key:String,
    currentValue: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
){

    var inputValue by remember { mutableStateOf(currentValue) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Update ${key.replaceFirstChar { it.uppercaseChar() }}")
        },
        text = {
            when (key) {
                "goal", "occupationType", "gender" -> {
                    val options = when (key) {
                        "goal" -> listOf("Lose Weight", "Maintain", "Gain Muscle")
                        "occupationType" -> listOf("Sedentary", "Active", "Very Active")
                        "gender" -> listOf("Male", "Female", "Other")
                        else -> emptyList()
                    }

                    Column {
                        options.forEach { option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { inputValue = option }
                                    .padding(8.dp)
                            ) {
                                RadioButton(
                                    selected = inputValue == option,
                                    onClick = { inputValue = option }
                                )
                                Text(option)
                            }
                        }
                    }
                }

                else -> {
                    OutlinedTextField(
                        value = inputValue,
                        onValueChange = { inputValue = it },
                        label = { Text("Enter $key") },
                        singleLine = true
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(inputValue) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}
