package com.example.databasetestingwithhilt.Authentications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.R


@Composable
fun SignUpScreen(navController: NavController) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFBDD7F2), Color.White)))
        ) {
            // Back Button (Top-left)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Black, shape = CircleShape)
                    )
                }
            }

            // Bottom Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)  // Covers 1/4th of the screen height
                    .align(Alignment.BottomCenter)
                    .clip(
                        RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    ) // Rounded top corners
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Get Started",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007AFF)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Input Fields
                    val fullName = remember { mutableStateOf("") }
                    val email = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val isChecked = remember { mutableStateOf(false) }

                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        OutlinedTextField(
                            value = fullName.value,
                            onValueChange = { fullName.value = it },
                            label = { Text("Enter Full Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = email.value,
                            onValueChange = { email.value = it },
                            label = { Text("Enter Email") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            label = { Text("Enter Password") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Terms and Conditions Checkbox
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked.value,
                                onCheckedChange = { isChecked.value = it }
                            )
                            ClickableText(
                                text = AnnotatedString("I agree to the processing of Personal data"),
                                onClick = { /* Handle link click */ },
                                style = TextStyle(color = Color.Blue, fontSize = 14.sp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Sign-Up Button
                        Button(
                            onClick = { /* Handle Sign-Up */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            //   colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007AFF))
                        ) {
                            Text(text = "Sign up", color = Color.White, fontSize = 18.sp)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Sign-Up with Social Media
                        Text("Sign-up with", fontSize = 16.sp, color = Color.Gray)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = { /* Handle Facebook Login */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.facebook),
                                    contentDescription = "Facebook",
                                    tint = Color.Unspecified
                                )
                            }
                            IconButton(onClick = { /* Handle Google Login */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = "Google",
                                    tint = Color.Unspecified
                                )
                            }
                            IconButton(onClick = { /* Handle Apple Login */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.apple),
                                    contentDescription = "Apple",
                                    tint = Color.Unspecified
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Already have an account?
                        Row {
                            Text("Already have an account?", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(5.dp))
                            ClickableText(
                                text = AnnotatedString("Sign in"),
                                onClick = { navController.navigate("SignIn") },
                                style = TextStyle(
                                    color = Color.Blue,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
    }

