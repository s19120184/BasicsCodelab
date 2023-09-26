package com.example.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.hardware.biometrics.BiometricManager
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme
import com.google.firebase.inappmessaging.model.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(
       modifier: Modifier= Modifier,
){
    var mostrarMyApp by rememberSaveable { mutableStateOf(true) }
     Surface(modifier){
         if(mostrarMyApp){
             pantallaInico(onContinueClicked= { mostrarMyApp=false})
         }else{
             superCosas()
         }
     }
}

@Composable
private fun superCosas( modifier: Modifier = Modifier ,nombres: List<String> =List(1000){"$it"})
                          {
                              //
                              LazyColumn (modifier= modifier.padding(vertical = 4.dp)){
                                    items(items = nombres){nombre->
                                        Greeting(name = nombre)
                                    }
                                  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //variable de estado
    var expandir by remember { mutableStateOf(false)
    }
    //agregamos paddin segin el estado de expandir con animacion
     //animacion basada en resortes
    /*val paddingExtra by animateDpAsState(
            if(expandir) 48.dp else 0.dp,
            animationSpec = spring(
            dampingRatio= Spring.DampingRatioMediumBouncy,
            stiffness =  Spring.StiffnessLow), label = ""
    )*/

    //camniar el color de la superficie
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier= Modifier.padding(vertical = 4.dp , horizontal = 8.dp)){
        Row( modifier=Modifier.padding(24.dp)
               .animateContentSize(//animacion resorte
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ))) {
            //columnas
            Column(
                //rellena todo el espacio disponible
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)//cotrola el padding segunnel estado de expandir
            ) {

                Text(text = "Hello !")
                Text(text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))

                if(expandir) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }//ternina el colum
               /* ElevatedButton(onClick = { expandir = !expandir}) {
                    // segun el estado se mostrara uno u otro mensaje
                    Text(if (expandir) "Mostrar menos" else "Mostrar mas")
                }*/
            IconButton(onClick = { expandir = !expandir }) {
                Icon(
                    imageVector = if (expandir) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expandir) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }

}
@Composable
fun pantallaInico( onContinueClicked: () -> Unit, modifier: Modifier = Modifier){

    //toma toda la pantalla centrado vertical y horizontalmente
    Column ( modifier= modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text ="Welcome to ..")
           Button(
               modifier = Modifier.padding(vertical = 25.dp),
               onClick=onContinueClicked){
               Text(text = "Continua")
           }

    }
}

@Preview
@Composable
fun pantallaIni() {
    BasicsCodelabTheme {
        pantallaInico(onContinueClicked = {})
    }
}

@Preview
@Composable
fun SupercosasPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true , widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}


//vista previa modo oscuro
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name="Dark"
)
@Preview(showBackground = true , widthDp = 320)
@Composable
fun DefaultPreview(){
    BasicsCodelabTheme {
        superCosas()
    }
}