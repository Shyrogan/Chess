package fr.chess.game.window.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Le {@link ImageManager} est un peu complexe mais en gros il permet d'éviter
 * de charger deux fois une même image.
 */
public class ImageManager {

    private final static Map<String, BufferedImage> CACHE = new HashMap<>();

    /**
     * En gros cette méthode est un peu complexe mais assez simple à comprendre.
     * Elle essaye de charger l'image qui a pour nom le paramètre {@code name} et s'il ne l'a pas déjà chargé,
     * il va invoquer le second paramètre qui est une "petite fonction" permettant de charger l'image.
     *
     * @param name Nom de l'image
     * @param loader Loader de l'image
     * @return L'image
     */
    public static BufferedImage load(String name, Supplier<BufferedImage> loader) {
        BufferedImage image = CACHE.get(name);
        if(image != null) return image;

        image = loader.get();
        CACHE.put(name, image);
        return image;
    }

    /**
     * Charge une image depuis les ressources, c'est-à-dire les fichiers proposés avec le programme.
     * @param name Le nom de l'image et son type
     * @return L'image
     */
    public static BufferedImage resource(String name) {
        return load(name, () -> {
            try {
                return ImageIO.read(requireNonNull(ImageManager.class.getResource("/image/" + name)));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

}
